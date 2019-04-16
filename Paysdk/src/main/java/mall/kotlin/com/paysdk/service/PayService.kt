package mall.kotlin.com.paysdk.service

import rx.Observable

/**
 * author:  zhangkun .
 * date:    on 2018/10/25.
 */
interface PayService {


    /*
       获取支付宝支付签名
    */
    fun getPaySign(orderId: Int, totalPrice: Long): Observable<String>

}