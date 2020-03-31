package mall.kotlin.com.littlekotlinmall.ui.fragment

import android.support.v7.widget.LinearLayoutManager
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import kotlinx.android.synthetic.main.fragment_home.*
import mall.kotlin.com.baselibrary.ext.onClick
import mall.kotlin.com.baselibrary.ui.fragment.BaseUIFragment
import mall.kotlin.com.baselibrary.widgets.BannerImageLoader
import mall.kotlin.com.goodscenter.ui.activity.SearchGoodsActivity
import mall.kotlin.com.littlekotlinmall.R
import mall.kotlin.com.littlekotlinmall.commom.*
import mall.kotlin.com.littlekotlinmall.ui.adapter.HomeDiscountAdapter
import mall.kotlin.com.littlekotlinmall.ui.adapter.TopicAdapter
import me.crosswall.lib.coverflow.CoverFlow
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast


/**
 * author:  zhangkun .
 * date:    on 2019/1/22.
 */
class HomeFragment : BaseUIFragment() {
    override fun setView(): Int {
        return R.layout.fragment_home
    }

    override fun initView() {

        initBanner()
        initNews()
        initDiscount()
        initTopic()
    }

    override fun setListener() {
        mSearchEt.onClick {
            startActivity<SearchGoodsActivity>()
        }

        mScanIv.onClick {
            toast(R.string.coming_soon_tip)
        }

    }


    fun initBanner() {
        //设置banner样式
        //mHomeBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE)
        //设置图片加载器
        mHomeBanner.setImageLoader(BannerImageLoader())
        //设置图片集合
        mHomeBanner.setImages(listOf(HOME_BANNER_ONE, HOME_BANNER_TWO, HOME_BANNER_THREE, HOME_BANNER_FOUR))
        //设置banner动画效果
        mHomeBanner.setBannerAnimation(Transformer.DepthPage)
        //设置标题集合（当banner样式有显示title时）
        //mHomeBanner.setBannerTitles(titles)
        //设置自动轮播，默认为true
        mHomeBanner.isAutoPlay(true)
        //设置轮播时间
        mHomeBanner.setDelayTime(1500)
        //设置指示器位置（当banner模式中有指示器时）
        mHomeBanner.setIndicatorGravity(BannerConfig.RIGHT)
        //banner设置方法全部调用完毕时最后调用
        mHomeBanner.start()
    }

    fun initNews() {
        // 公告
        mNewsFlipperView.setData(arrayOf("夏日炎炎，第一波福利还有30秒到达战场", "新用户立领1000元优惠券"))

    }

    fun initDiscount() {
        val manager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        mHomeDiscountRv.layoutManager = manager
        val discountAdapter = HomeDiscountAdapter(activity!!)
        mHomeDiscountRv.adapter = discountAdapter
        discountAdapter.setData(
                mutableListOf(HOME_DISCOUNT_ONE,
                        HOME_DISCOUNT_TWO,
                        HOME_DISCOUNT_THREE,
                        HOME_DISCOUNT_FOUR
                        , HOME_DISCOUNT_FIVE)
        )

    }

    /*
        初始化主题
     */
    private fun initTopic() {
        //话题
        mTopicPager.adapter = TopicAdapter(context!!, listOf(HOME_TOPIC_ONE, HOME_TOPIC_TWO, HOME_TOPIC_THREE, HOME_TOPIC_FOUR, HOME_TOPIC_FIVE))
        mTopicPager.currentItem = 1
        mTopicPager.offscreenPageLimit = 5

        CoverFlow.Builder().with(mTopicPager)
                .scale(0.3f)
                .pagerMargin(-30.0f)
                .spaceSize(0.0f)
                .build()
    }


    override fun start() {

    }
}