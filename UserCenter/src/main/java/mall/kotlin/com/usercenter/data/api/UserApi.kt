package mall.kotlin.com.usercenter.data.api

import mall.kotlin.com.baselibrary.data.protocol.BaseResp
import mall.kotlin.com.usercenter.data.protocol.*
import retrofit2.http.Body
import retrofit2.http.POST
import rx.Observable

/**
 *    author : zhangkun .
 *    date   : on 2018/10/29
 */
interface UserApi {

    @POST("userCenter/register")
    fun register(@Body req: RegisterReq): Observable<BaseResp<String>>

    @POST("userCenter/login")
    fun login(@Body req: LoginReq): Observable<BaseResp<UserInfo>>

    @POST("userCenter/forgetPwd")
    fun forgetPwd(@Body req: ForgetPwdReq): Observable<BaseResp<String>>

    @POST("userCenter/resetPwd")
    fun resetPwd(@Body req: ResetPwdReq): Observable<BaseResp<String>>

    @POST("userCenter/editUser")
    fun editUser(@Body req: EditUserReq): Observable<BaseResp<UserInfo>>
}