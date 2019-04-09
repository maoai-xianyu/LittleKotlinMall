package mall.kotlin.com.ordercenter.ui.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.kennyc.view.MultiStateView
import kotlinx.android.synthetic.main.fragment_order.*
import mall.kotlin.com.baselibrary.ext.startLoading
import mall.kotlin.com.baselibrary.ui.fragment.BaseMvpFragment
import mall.kotlin.com.ordercenter.R
import mall.kotlin.com.ordercenter.common.OrderConstant
import mall.kotlin.com.ordercenter.data.protocol.Order
import mall.kotlin.com.ordercenter.injection.component.DaggerOrderComponent
import mall.kotlin.com.ordercenter.injection.module.OrderModule
import mall.kotlin.com.ordercenter.presenter.OrderListPresenter
import mall.kotlin.com.ordercenter.presenter.view.OrderListView
import mall.kotlin.com.ordercenter.ui.adapter.OrderAdapter

/**
 * author:    zhangkun .
 * date:    on 2019/4/8.
 */
class OrderFragment : BaseMvpFragment<OrderListPresenter>(), OrderListView {

    private var orderStatus: Int = -1
    private lateinit var orderAdapter: OrderAdapter

    override fun setView(): Int {
        return R.layout.fragment_order
    }

    override fun getArgs(bundle: Bundle?) {
        bundle?.let {
            orderStatus = bundle.getInt(OrderConstant.KEY_ORDER_STATUS, -1)
        }

    }

    override fun initView() {
        mOrderRv.layoutManager = LinearLayoutManager(context)
        orderAdapter = OrderAdapter(context!!)
        mOrderRv.adapter = orderAdapter
    }

    override fun setListener() {

    }


    override fun start() {
        loadData()
    }

    private fun loadData() {
        mMultiStateView.startLoading()
        mPresenter.getOrderList(orderStatus)
    }


    override fun injectComponent() {
        DaggerOrderComponent.builder().activityComponent(activityComponent)
                .orderModule(OrderModule())
                .build()
                .inject(this)
        mPresenter.mView = this

    }

    override fun onGetOrderListResult(result: MutableList<Order>?) {
        if (result != null && result.size > 0) {
            orderAdapter.setData(result)
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_CONTENT
        } else {
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_EMPTY
        }
    }
}