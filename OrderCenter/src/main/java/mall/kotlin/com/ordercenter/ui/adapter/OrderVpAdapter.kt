package mall.kotlin.com.ordercenter.ui.adapter

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import mall.kotlin.com.ordercenter.common.OrderConstant
import mall.kotlin.com.ordercenter.ui.fragment.OrderFragment

/**
 * author:  zhangkun .
 * date:    on 2019/3/12.
 */
class OrderVpAdapter(fm: androidx.fragment.app.FragmentManager, context: Context) : androidx.fragment.app.FragmentPagerAdapter(fm) {


    private val titles = arrayOf("全部", "待付款", "待收货", "已完成", "已取消")

    override fun getItem(p0: Int): androidx.fragment.app.Fragment {
        val fragment = OrderFragment()
        val bundle = Bundle()
        bundle.putInt(OrderConstant.KEY_ORDER_STATUS, p0)
        fragment.arguments = bundle
        return fragment
    }

    override fun getCount(): Int {
        return titles.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titles[position]
    }
}