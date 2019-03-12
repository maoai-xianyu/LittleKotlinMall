package mall.kotlin.com.goodscenter.ui.adapter

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import mall.kotlin.com.goodscenter.ui.fragment.GoodsDetailTabOneFragment
import mall.kotlin.com.goodscenter.ui.fragment.GoodsDetailTabTwoFragment

/**
 * author:  zhangkun .
 * date:    on 2019/3/12.
 */
class GoodsDetailVpAdapter(fm: FragmentManager, context: Context) : FragmentPagerAdapter(fm) {


    private val titles = arrayOf("商品", "详情")

    override fun getItem(p0: Int): Fragment {
        return if (p0 == 0) {
            GoodsDetailTabOneFragment()
        } else {
            GoodsDetailTabTwoFragment()
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titles[position]
    }
}