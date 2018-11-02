package mall.kotlin.com.usercenter.injection.component

import dagger.Component
import mall.kotlin.com.usercenter.injection.module.UserModule
import mall.kotlin.com.usercenter.ui.activity.RegisterActivity

/**
 * author:  zhangkun .
 * date:    on 2018/11/2.
 */
@Component(
        modules = [UserModule::class]
)
interface UserComponent {

    fun inject(activity: RegisterActivity)


}