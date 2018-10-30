package mall.kotlin.com.usercenter.data.api

import mall.kotlin.com.baselibrary.data.protocol.BaseResp
import mall.kotlin.com.usercenter.data.protocol.RegisterReq
import retrofit2.http.Body
import retrofit2.http.POST
import rx.Observable

/**
 *    author : zhangkun .
 *    date   : on 2018/10/29
 */
interface UserApi {

    @POST("userCenter/register")
    fun register(@Body req:RegisterReq):Observable<BaseResp<String>>
}