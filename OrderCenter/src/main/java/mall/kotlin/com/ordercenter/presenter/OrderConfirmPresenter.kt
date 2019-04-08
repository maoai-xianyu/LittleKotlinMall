package mall.kotlin.com.ordercenter.presenter

import mall.kotlin.com.baselibrary.ext.execute
import mall.kotlin.com.baselibrary.presenter.BasePresenter
import mall.kotlin.com.baselibrary.rx.BaseSubscriber
import mall.kotlin.com.ordercenter.data.protocol.Order
import mall.kotlin.com.ordercenter.presenter.view.OrderConfirmView
import mall.kotlin.com.ordercenter.service.OrderService
import javax.inject.Inject

/**
 * author:  zhangkun .
 * date:    on 2018/10/25.
 */
class OrderConfirmPresenter @Inject constructor() : BasePresenter<OrderConfirmView>() {

    @Inject
    lateinit var orderService: OrderService


    fun getOrderById(orderId: Int) {
        /**
         * 业务逻辑
         */
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        orderService.getOrderById(orderId)
                .execute(object : BaseSubscriber<Order>(mView) {
                    override fun onNext(t: Order) {
                        mView.onGetOrderByIdResult(t)
                    }
                }, lifecycleProvider)

    }

    fun submitOrder(order: Order) {
        /**
         * 业务逻辑
         */
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        orderService.submitOrder(order)
                .execute(object : BaseSubscriber<Boolean>(mView) {
                    override fun onNext(t: Boolean) {
                        mView.onSubmitOrderResult(t)
                    }
                }, lifecycleProvider)

    }
}