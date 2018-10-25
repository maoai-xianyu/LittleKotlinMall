package mall.kotlin.com.usercenter.service

import rx.Observable

/**
 * author:  zhangkun .
 * date:    on 2018/10/25.
 */
interface UserService {
    fun register(mobile:String,verifyCode:String,pwd:String):Observable<Boolean>
}