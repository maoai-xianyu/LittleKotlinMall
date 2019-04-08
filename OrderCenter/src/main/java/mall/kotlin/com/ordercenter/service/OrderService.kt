package mall.kotlin.com.ordercenter.service

import mall.kotlin.com.ordercenter.data.protocol.Order
import rx.Observable

/**
 * author:  zhangkun .
 * date:    on 2018/10/25.
 */
interface OrderService {
    fun getOrderById(orderId: Int): Observable<Order>

    fun submitOrder(order: Order): Observable<Boolean>
}