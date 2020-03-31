package mall.kotlin.com.ordercenter.ui.activity

import android.os.Bundle
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_order.*
import mall.kotlin.com.baselibrary.ui.activity.BaseUIActivity
import mall.kotlin.com.ordercenter.R
import mall.kotlin.com.ordercenter.common.OrderConstant
import mall.kotlin.com.ordercenter.common.OrderStatus
import mall.kotlin.com.ordercenter.ui.adapter.OrderVpAdapter

/**
 * author:    zhangkun .
 * date:    on 2019/4/8.
 */
class OrderActivity : BaseUIActivity() {


    private var currentPosition: Int = 0

    override fun setView(): Int {
        return R.layout.activity_order
    }


    override fun getArgs(bundle: Bundle?) {
        bundle?.let {
            currentPosition = it.getInt(OrderConstant.KEY_ORDER_STATUS, OrderStatus.ORDER_ALL)
        }
    }

    override fun initView() {
        mOrderTab.tabMode = TabLayout.MODE_FIXED
        mOrderVp.adapter = OrderVpAdapter(supportFragmentManager, this)
        mOrderTab.setupWithViewPager(mOrderVp)

        mOrderVp.currentItem = currentPosition
    }

}