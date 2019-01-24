package mall.kotlin.com.littlekotlinmall.ui.fragment

import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import kotlinx.android.synthetic.main.fragment_home.*
import mall.kotlin.com.baselibrary.ui.fragment.BaseUIFragment
import mall.kotlin.com.baselibrary.widgets.BannerImageLoader
import mall.kotlin.com.littlekotlinmall.R
import mall.kotlin.com.littlekotlinmall.commom.HOME_BANNER_FOUR
import mall.kotlin.com.littlekotlinmall.commom.HOME_BANNER_ONE
import mall.kotlin.com.littlekotlinmall.commom.HOME_BANNER_THREE
import mall.kotlin.com.littlekotlinmall.commom.HOME_BANNER_TWO


/**
 * author:  zhangkun .
 * date:    on 2019/1/22.
 */
class HomeFragment : BaseUIFragment() {
    override fun setView(): Int {
        return R.layout.fragment_home
    }

    override fun initView() {
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

    override fun start() {

    }
}