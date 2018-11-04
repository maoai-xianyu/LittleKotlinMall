package mall.kotlin.com.usercenter.service.impl

import mall.kotlin.com.baselibrary.data.protocol.BaseResp
import mall.kotlin.com.baselibrary.rx.BaseException
import mall.kotlin.com.usercenter.data.repository.UserRepository
import mall.kotlin.com.usercenter.service.UserService
import rx.Observable
import rx.functions.Func1
import javax.inject.Inject

/**
 *    author : zhangkun .
 *    date   : on 2018/10/25
 */
class UserServiceImplNamed @Inject constructor() : UserService {

    @Inject
    lateinit var repository: UserRepository

    override fun register(mobile: String, pwd: String, verifyCode: String): Observable<Boolean> {
        return repository.register(mobile, pwd, verifyCode).flatMap(object : Func1<BaseResp<String>, Observable<Boolean>> {
            override fun call(t: BaseResp<String>): Observable<Boolean> {
                if (t.status != 0){
                    return Observable.error(BaseException(t.status, t.message))
                }
                return Observable.just(false)
            }

        })
    }
}