package mall.kotlin.com.ordercenter.service.impl

import mall.kotlin.com.baselibrary.ext.convert
import mall.kotlin.com.ordercenter.data.protocol.Order
import mall.kotlin.com.ordercenter.data.repository.OrderRepository
import mall.kotlin.com.ordercenter.service.OrderService
import rx.Observable
import javax.inject.Inject

/**
 *    author : zhangkun .
 *    date   : on 2018/10/25
 */
class OrderServiceImpl @Inject constructor() : OrderService {

    @Inject
    lateinit var repository: OrderRepository

    override fun getOrderById(orderId: Int): Observable<Order> {
        return repository.getOrderById(orderId).convert()
    }
}