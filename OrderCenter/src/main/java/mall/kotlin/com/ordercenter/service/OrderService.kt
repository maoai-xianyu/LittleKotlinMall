package mall.kotlin.com.ordercenter.service

import mall.kotlin.com.ordercenter.data.protocol.Order
import rx.Observable

/**
 * author:  zhangkun .
 * date:    on 2018/10/25.
 */
interface OrderService {

    /*
        根据ID查询订单
     */
    fun getOrderById(orderId: Int): Observable<Order>

    /*
       提交订单
    */
    fun submitOrder(order: Order): Observable<Boolean>

    /*
        根据状态查询订单列表
     */
    fun getOrderList(orderStatus: Int): Observable<MutableList<Order>?>


}