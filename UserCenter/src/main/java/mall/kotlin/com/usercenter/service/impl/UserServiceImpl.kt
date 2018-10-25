package mall.kotlin.com.usercenter.service.impl

import mall.kotlin.com.usercenter.service.UserService
import rx.Observable

/**
 *    author : zhangkun .
 *    date   : on 2018/10/25
 */
class UserServiceImpl : UserService {
    override fun register(mobile: String, verifyCode: String, pwd: String): Observable<Boolean> {
        return Observable.just(true)
    }
}