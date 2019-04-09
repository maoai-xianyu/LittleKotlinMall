package mall.kotlin.com.ordercenter.presenter.view

import mall.kotlin.com.baselibrary.presenter.view.BaseView
import mall.kotlin.com.ordercenter.data.protocol.Order

/**
 * author:  zhangkun .
 * date:    on 2018/10/25.
 */
interface OrderListView : BaseView {
    fun onGetOrderListResult(result: MutableList<Order>?)
}