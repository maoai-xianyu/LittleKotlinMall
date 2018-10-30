package mall.kotlin.com.usercenter.data.repository

import mall.kotlin.com.baselibrary.data.net.RetrofitFactory
import mall.kotlin.com.baselibrary.data.protocol.BaseResp
import mall.kotlin.com.usercenter.data.api.UserApi
import mall.kotlin.com.usercenter.data.protocol.RegisterReq
import rx.Observable

/**
 *    author : zhangkun .
 *    date   : on 2018/10/29
 */
class UserRepository {

    fun register(mobile: String, pwd: String, verifyCode: String): Observable<BaseResp<String>> {
        return RetrofitFactory.instance.create(UserApi::class.java)
                .register(RegisterReq(mobile, pwd, verifyCode))
    }
}