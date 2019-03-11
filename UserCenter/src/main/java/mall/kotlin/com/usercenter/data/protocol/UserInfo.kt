package mall.kotlin.com.usercenter.data.protocol

/**
 *    author : zhangkun .
 *    date   : on 2018/10/29
 *    用户实体类
 */
data class UserInfo(val id: String,
                    var userIcon: String,
                    val userName: String,
                    val userGender: String,
                    val userMobile: String,
                    val userSign: String
)