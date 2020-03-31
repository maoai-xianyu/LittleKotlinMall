package mall.kotlin.com.goodscenter.ui.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import mall.kotlin.com.goodscenter.ui.fragment.GoodsDetailTabOneFragment
import mall.kotlin.com.goodscenter.ui.fragment.GoodsDetailTabTwoFragment

/**
 * author:  zhangkun .
 * date:    on 2019/3/12.
 */
class GoodsDetailVpAdapter(fm: androidx.fragment.app.FragmentManager, context: Context) : androidx.fragment.app.FragmentPagerAdapter(fm) {


    private val titles = arrayOf("商品", "详情")

    override fun getItem(p0: Int): androidx.fragment.app.Fragment {
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