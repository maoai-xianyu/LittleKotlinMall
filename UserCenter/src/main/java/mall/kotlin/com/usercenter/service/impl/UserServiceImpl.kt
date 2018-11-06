package mall.kotlin.com.usercenter.service.impl

import mall.kotlin.com.baselibrary.rx.BaseFuncBoolean
import mall.kotlin.com.usercenter.data.repository.UserRepository
import mall.kotlin.com.usercenter.service.UserService
import rx.Observable
import javax.inject.Inject

/**
 *    author : zhangkun .
 *    date   : on 2018/10/25
 */
class UserServiceImpl @Inject constructor() : UserService {

    @Inject
    lateinit var repository: UserRepository

    override fun register(mobile: String, pwd: String, verifyCode: String): Observable<Boolean> {
        return repository.register(mobile, pwd, verifyCode)
                .flatMap(BaseFuncBoolean())
    }
}