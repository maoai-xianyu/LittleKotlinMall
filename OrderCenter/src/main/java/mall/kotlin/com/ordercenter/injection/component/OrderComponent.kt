package mall.kotlin.com.ordercenter.injection.component

import dagger.Component
import mall.kotlin.com.baselibrary.injection.PerComponentScope
import mall.kotlin.com.baselibrary.injection.component.ActivityComponent
import mall.kotlin.com.ordercenter.injection.module.OrderModule
import mall.kotlin.com.ordercenter.ui.activity.OrderConfirmActivity
import mall.kotlin.com.ordercenter.ui.activity.OrderDetailActivity
import mall.kotlin.com.ordercenter.ui.fragment.OrderFragment

/**
 * author:  zhangkun .
 * date:    on 2018/11/2.
 */
@PerComponentScope
@Component(
        dependencies = [ActivityComponent::class],
        modules = [OrderModule::class]
)
interface OrderComponent {
    fun inject(activity: OrderConfirmActivity)
    fun inject(activity: OrderDetailActivity)
    fun inject(fragment: OrderFragment)
}