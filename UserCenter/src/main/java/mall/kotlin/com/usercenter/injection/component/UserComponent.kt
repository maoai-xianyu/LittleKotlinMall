package mall.kotlin.com.usercenter.injection.component

import dagger.Component
import mall.kotlin.com.baselibrary.injection.PerComponentScope
import mall.kotlin.com.baselibrary.injection.component.ActivityComponent
import mall.kotlin.com.usercenter.injection.module.UploadModule
import mall.kotlin.com.usercenter.injection.module.UserModule
import mall.kotlin.com.usercenter.ui.activity.*

/**
 * author:  zhangkun .
 * date:    on 2018/11/2.
 */
@PerComponentScope
@Component(
        dependencies = [ActivityComponent::class],
        modules = [UserModule::class, UploadModule::class]
)
interface UserComponent {

    fun inject(activity: RegisterActivity)
    fun inject(activity: LoginActivity)
    fun inject(activity: ForgetPwdPwdActivity)
    fun inject(activity: ResetPwdActivity)
    fun inject(activity: UserInfoActivity)

}