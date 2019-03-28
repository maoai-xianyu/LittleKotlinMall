package mall.kotlin.com.provider.common

import com.alibaba.android.arouter.launcher.ARouter
import mall.kotlin.com.baselibrary.common.BaseConstant
import mall.kotlin.com.baselibrary.utils.AppPrefsUtils
import mall.kotlin.com.provider.router.RouterPath

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

fun afterLogin(method: () -> Unit) {
    if (isLogined()) {
        method()
    } else {
        ARouter.getInstance().build(RouterPath.UserCenter.PATH_LOGIN).navigation()

    }

}