package mall.kotlin.com.provider.common

import mall.kotlin.com.baselibrary.common.BaseConstant
import mall.kotlin.com.baselibrary.utils.AppPrefsUtils

/**
 * author:  zhangkun .
 * date:    on 2018/10/25.
 */

/**
 * 全局函数
 */
/**
 * 是否登录
 */
fun isLogined(): Boolean {
    return AppPrefsUtils.getString(BaseConstant.KEY_SP_TOKEN).isNotEmpty()
}