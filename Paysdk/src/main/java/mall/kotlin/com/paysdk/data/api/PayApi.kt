package mall.kotlin.com.paysdk.data.api

import retrofit2.http.Body
import rx.Observable
import mall.kotlin.com.paysdk.data.protocol.GetPaySignReq
import mall.kotlin.com.paysdk.data.protocol.PayOrderReq
import mall.kotlin.com.baselibrary.data.protocol.BaseResp
import retrofit2.http.POST


/*
    支付 接口
 */
interface PayApi {

    /*
        获取支付宝支付签名
     */
    @POST("pay/getPaySign")
    fun getPaySign(@Body req: GetPaySignReq): Observable<BaseResp<String>>

    /*
        刷新订单状态，已支付
     */
    @POST("order/pay")
    fun payOrder(@Body req: PayOrderReq): Observable<BaseResp<String>>

}
