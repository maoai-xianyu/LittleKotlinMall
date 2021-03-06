package mall.kotlin.com.usercenter.service

import mall.kotlin.com.usercenter.data.protocol.UserInfo
import rx.Observable

/**
 * author:  zhangkun .
 * date:    on 2018/10/25.
 */
interface UserService {
    fun register(mobile: String, pwd: String, verifyCode: String): Observable<Boolean>
    fun login(mobile: String, pwd: String, pushId: String): Observable<UserInfo>
    fun forgetPwd(mobile: String, verifyCode: String): Observable<Boolean>
    fun resetPwd(mobile: String, pwd: String): Observable<Boolean>
    fun editUser(userIcon: String, userName: String, userGender: String, userSign: String): Observable<UserInfo>

}