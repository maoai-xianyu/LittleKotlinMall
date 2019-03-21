package mall.kotlin.com.goodscenter.ui.fragment

import com.eightbitlab.rxbus.Bus
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import kotlinx.android.synthetic.main.fragment_goods_detail_tab_one.*
import mall.kotlin.com.baselibrary.ui.fragment.BaseMvpFragment
import mall.kotlin.com.baselibrary.utils.YuanFenConverter
import mall.kotlin.com.baselibrary.widgets.BannerImageLoader
import mall.kotlin.com.goodscenter.R
import mall.kotlin.com.goodscenter.common.GoodsConstant
import mall.kotlin.com.goodscenter.data.protocol.Goods
import mall.kotlin.com.goodscenter.event.GoodsDetailImageEvent
import mall.kotlin.com.goodscenter.injection.component.DaggerGoodsComponent
import mall.kotlin.com.goodscenter.injection.module.GoodsModule
import mall.kotlin.com.goodscenter.presenter.GoodsDetailPresenter
import mall.kotlin.com.goodscenter.presenter.view.GoodsDetailView

/**
 * authorhangkun .
 * date:    on 2019/3/11.
 */
class GoodsDetailTabOneFragment : BaseMvpFragment<GoodsDetailPresenter>(), GoodsDetailView {


    override fun setView(): Int {
        return R.layout.fragment_goods_detail_tab_one
    }

    override fun initView() {
        initBanner()
    }

    override fun setListener() {

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

    override fun onGetGoodsDetailResult(result: Goods) {
        //设置图片集合
        mGoodsDetailBanner.setImages(result.goodsBanner.split(","))
        //banner设置方法全部调用完毕时最后调用
        mGoodsDetailBanner.start()

        mGoodsDescTv.text = result.goodsDesc
        mGoodsPriceTv.text = YuanFenConverter.changeF2YWithUnit(result.goodsDefaultPrice)

        mSkuSelectedTv.text = result.goodsDefaultSku


        Bus.send(GoodsDetailImageEvent(result.goodsDetailOne,result.goodsDetailTwo))

    }
}