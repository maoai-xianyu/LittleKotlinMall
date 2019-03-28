package mall.kotlin.com.goodscenter.ui.fragment

import android.annotation.SuppressLint
import android.view.Gravity
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import com.eightbitlab.rxbus.Bus
import com.eightbitlab.rxbus.registerInBus
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import kotlinx.android.synthetic.main.fragment_goods_detail_tab_one.*
import mall.kotlin.com.baselibrary.ext.onClick
import mall.kotlin.com.baselibrary.ui.activity.BaseActivity
import mall.kotlin.com.baselibrary.ui.fragment.BaseMvpFragment
import mall.kotlin.com.baselibrary.utils.YuanFenConverter
import mall.kotlin.com.baselibrary.widgets.BannerImageLoader
import mall.kotlin.com.goodscenter.R
import mall.kotlin.com.goodscenter.common.GoodsConstant
import mall.kotlin.com.goodscenter.data.protocol.Goods
import mall.kotlin.com.goodscenter.event.AddCartEvent
import mall.kotlin.com.goodscenter.event.GoodsDetailImageEvent
import mall.kotlin.com.goodscenter.event.SkuChangedEvent
import mall.kotlin.com.goodscenter.event.UpdateCartSizeEvent
import mall.kotlin.com.goodscenter.injection.component.DaggerGoodsComponent
import mall.kotlin.com.goodscenter.injection.module.GoodsModule
import mall.kotlin.com.goodscenter.presenter.GoodsDetailPresenter
import mall.kotlin.com.goodscenter.presenter.view.GoodsDetailView
import mall.kotlin.com.goodscenter.ui.activity.GoodsDetailActivity
import mall.kotlin.com.goodscenter.widget.GoodsSkuPopView

/**
 * authorhangkun .
 * date:    on 2019/3/11.
 */
class GoodsDetailTabOneFragment : BaseMvpFragment<GoodsDetailPresenter>(), GoodsDetailView {

    private lateinit var mSkuPop: GoodsSkuPopView
    // SKU弹层出场动画
    private lateinit var mAnimationStart: Animation
    // SKU弹层退场动画
    private lateinit var mAnimationEnd: Animation

    private var mCurGoods: Goods? = null

    override fun setView(): Int {
        return R.layout.fragment_goods_detail_tab_one
    }

    override fun initView() {
        initBanner()
        initAnim()
        initSkuPop()
        initObserve()
    }


    override fun setListener() {

        mSkuView.onClick {
            mSkuPop.showAtLocation((activity as GoodsDetailActivity).contentView,
                    Gravity.BOTTOM and Gravity.CENTER_HORIZONTAL, 0, 0)

            (activity as BaseActivity).contentView.startAnimation(mAnimationStart)
        }

        mSkuPop.setOnDismissListener {
            (activity as BaseActivity).contentView.startAnimation(mAnimationEnd)

        }

    }

    override fun start() {
        mPresenter.getGoodsDetail(activity!!.intent.getIntExtra(GoodsConstant.KEY_GOODS_ID, -1))
    }

    override fun injectComponent() {
        DaggerGoodsComponent.builder()
                .activityComponent(activityComponent)
                .goodsModule(GoodsModule())
                .build()
                .inject(this)

        mPresenter.mView = this
    }

    fun initBanner() {
        //设置banner样式
        //mHomeBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE)
        //设置图片加载器
        mGoodsDetailBanner.setImageLoader(BannerImageLoader())
        //设置banner动画效果
        mGoodsDetailBanner.setBannerAnimation(Transformer.DepthPage)
        //设置标题集合（当banner样式有显示title时）
        //mHomeBanner.setBannerTitles(titles)
        //设置自动轮播，默认为true
        mGoodsDetailBanner.isAutoPlay(true)
        //设置轮播时间
        mGoodsDetailBanner.setDelayTime(1500)
        //设置指示器位置（当banner模式中有指示器时）
        mGoodsDetailBanner.setIndicatorGravity(BannerConfig.RIGHT)

    }

    private fun initSkuPop() {
        mSkuPop = GoodsSkuPopView(activity!!)
    }

    @SuppressLint("SetTextI18n")
    private fun initObserve() {
        Bus.observe<SkuChangedEvent>()
                .subscribe {
                    mSkuSelectedTv.text = mSkuPop.getSelectSku() +
                            GoodsConstant.SKU_SEPARATOR + mSkuPop.getSelectCount() + "件"
                }.registerInBus(this)


        Bus.observe<AddCartEvent>()
                .subscribe {
                    addCart()
                }.registerInBus(this)
    }

    /**
     * 初始化缩放动画
     */
    private fun initAnim() {
        mAnimationStart = ScaleAnimation(1f, 0.95f, 1f, 0.95f, Animation.RELATIVE_TO_SELF,
                0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
        mAnimationStart.duration = 500
        mAnimationStart.fillAfter = true

        mAnimationEnd = ScaleAnimation(0.95f, 1f, 0.95f, 1f, Animation.RELATIVE_TO_SELF,
                0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
        mAnimationEnd.duration = 500
        mAnimationEnd.fillAfter = true


    }


    override fun onGetGoodsDetailResult(result: Goods) {
        mCurGoods = result
        //设置图片集合
        mGoodsDetailBanner.setImages(result.goodsBanner.split(","))
        //banner设置方法全部调用完毕时最后调用
        mGoodsDetailBanner.start()

        mGoodsDescTv.text = result.goodsDesc
        mGoodsPriceTv.text = YuanFenConverter.changeF2YWithUnit(result.goodsDefaultPrice)

        mSkuSelectedTv.text = result.goodsDefaultSku

        Bus.send(GoodsDetailImageEvent(result.goodsDetailOne, result.goodsDetailTwo))

        loadSkuPop(result)
    }

    private fun loadSkuPop(result: Goods) {
        mSkuPop.setGoodsIcon(result.goodsDefaultIcon)
        mSkuPop.setGoodsCode(result.goodsCode)
        mSkuPop.setGoodsPrice(result.goodsDefaultPrice)
        mSkuPop.setSkuData(result.goodsSku)
    }


    override fun onAddCartResult(result: Int) {
        Bus.send(UpdateCartSizeEvent())
    }

    private fun addCart() {
        mCurGoods?.let {
            mPresenter.addCart(it.id, it.goodsDesc, it.goodsDefaultIcon,
                    it.goodsDefaultPrice, mSkuPop.getSelectCount(), mSkuPop.getSelectSku())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Bus.unregister(this)
    }
}