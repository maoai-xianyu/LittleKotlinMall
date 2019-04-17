package mall.kotlin.com.ordercenter.ui.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.alibaba.android.arouter.launcher.ARouter
import com.bigkoo.alertview.AlertView
import com.bigkoo.alertview.OnItemClickListener
import com.kennyc.view.MultiStateView
import kotlinx.android.synthetic.main.fragment_order.*
import mall.kotlin.com.baselibrary.ext.startLoading
import mall.kotlin.com.baselibrary.ui.adapter.BaseRecyclerViewAdapter
import mall.kotlin.com.baselibrary.ui.fragment.BaseMvpFragment
import mall.kotlin.com.ordercenter.R
import mall.kotlin.com.ordercenter.common.OrderConstant
import mall.kotlin.com.ordercenter.data.protocol.Order
import mall.kotlin.com.ordercenter.injection.component.DaggerOrderComponent
import mall.kotlin.com.ordercenter.injection.module.OrderModule
import mall.kotlin.com.ordercenter.presenter.OrderListPresenter
import mall.kotlin.com.ordercenter.presenter.view.OrderListView
import mall.kotlin.com.ordercenter.ui.activity.OrderDetailActivity
import mall.kotlin.com.ordercenter.ui.adapter.OrderAdapter
import mall.kotlin.com.provider.common.ProviderConstant
import mall.kotlin.com.provider.router.RouterPath
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast

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

        orderAdapter.listener = object : OrderAdapter.OnOptClickListener {
            override fun onOptClick(optType: Int, order: Order) {
                when (optType) {

                    OrderConstant.OPT_ORDER_PAY -> {
                        toast("支付")
                        ARouter.getInstance()
                                .build(RouterPath.PaySDK.PATH_PAY)
                                .withInt(ProviderConstant.KEY_ORDER_ID, order.id)
                                .withLong(ProviderConstant.KEY_ORDER_PRICE, order.totalPrice)
                                .navigation()

                    }

                    OrderConstant.OPT_ORDER_CONFIRM -> {
                        toast("确认")
                        mPresenter.confirmOrder(order.id)
                    }

                    OrderConstant.OPT_ORDER_CANCEL -> {
                        toast("取消")
                        showCancelDialog(order)
                    }
                }
            }
        }

        orderAdapter.setOnItemClickListener(object : BaseRecyclerViewAdapter.OnItemClickListener<Order> {
            override fun onItemClick(item: Order, position: Int) {
                startActivity<OrderDetailActivity>(ProviderConstant.KEY_ORDER_ID to item.id)
            }
        })


    }

    private fun showCancelDialog(order: Order) {
        AlertView("取消订单", "确定取消订单么？",
                "取消", null, arrayOf("确定"),
                context, AlertView.Style.Alert,
                OnItemClickListener { o, position ->
                    if (position == 0) {
                        mPresenter.cancelOrder(order.id)
                    }
                }).show()
    }

    override fun start() {

    }

    override fun onStart() {
        super.onStart()
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

    override fun onConfirmOrderResult(result: Boolean) {
        toast("确认收货成功")
        loadData()

    }

    override fun onCancelOrderResult(result: Boolean) {
        toast("取消订单成功")
        loadData()
    }
}