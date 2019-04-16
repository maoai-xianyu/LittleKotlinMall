package mall.kotlin.com.paysdk.data.repository


import mall.kotlin.com.baselibrary.data.net.RetrofitFactory
import mall.kotlin.com.baselibrary.data.protocol.BaseResp
import mall.kotlin.com.paysdk.data.api.PayApi
import mall.kotlin.com.paysdk.data.protocol.GetPaySignReq
import mall.kotlin.com.paysdk.data.protocol.PayOrderReq
import rx.Observable
import javax.inject.Inject


/*
   支付数据层
 */
class PayRepository @Inject constructor() {

    /*
        获取支付宝支付签名
     */
    fun getPaySign(orderId: Int, totalPrice: Long): Observable<BaseResp<String>> {
        return RetrofitFactory.instance.create(PayApi::class.java).getPaySign(GetPaySignReq(orderId, totalPrice))
    }

    /*
        刷新订单状态已支付
     */
    fun payOrder(orderId: Int): Observable<BaseResp<String>> {
        return RetrofitFactory.instance.create(PayApi::class.java).payOrder(PayOrderReq(orderId))
    }


}
