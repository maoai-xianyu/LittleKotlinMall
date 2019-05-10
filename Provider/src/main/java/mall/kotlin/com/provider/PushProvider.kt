package mall.kotlin.com.provider

import com.alibaba.android.arouter.facade.template.IProvider

/**
 * author:    zhangkun .
 * date:    on 2019-05-10.
 */
interface PushProvider : IProvider {
    fun getPushId(): String
}