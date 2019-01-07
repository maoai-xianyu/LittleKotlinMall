package mall.kotlin.com.usercenter.service.impl

import mall.kotlin.com.baselibrary.rx.BaseFunc
import mall.kotlin.com.baselibrary.rx.BaseFuncBoolean
import mall.kotlin.com.usercenter.data.protocol.UserInfo
import mall.kotlin.com.usercenter.data.repository.UserRepository
import mall.kotlin.com.usercenter.service.UserService
import rx.Observable
import javax.inject.Inject

/**
 *    author : zhangkun .
 *    date   : on 2018/10/25
 */
class UserServiceImplNamed @Inject constructor() : UserService {

    @Inject
    lateinit var repository: UserRepository

    override fun register(mobile: String, pwd: String, verifyCode: String): Observable<Boolean> {
        return repository.register(mobile, pwd, verifyCode)
                .flatMap(BaseFuncBoolean())
    }

    override fun login(mobile: String, pwd: String, pushId: String): Observable<UserInfo> {
        return repository.login(mobile, pwd, pushId)
                .flatMap(BaseFunc())
    }
}