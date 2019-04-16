package mall.kotlin.com.paysdk.injection.component

import dagger.Component
import mall.kotlin.com.baselibrary.injection.PerComponentScope
import mall.kotlin.com.baselibrary.injection.component.ActivityComponent
import mall.kotlin.com.paysdk.injection.module.PayModule
import mall.kotlin.com.paysdk.ui.activity.CashRegisterActivity

/**
 * author:  zhangkun .
 * date:    on 2018/11/2.
 */
@PerComponentScope
@Component(
        dependencies = [ActivityComponent::class],
        modules = [PayModule::class]
)
interface PayComponent {
    fun inject(activity: CashRegisterActivity)
}