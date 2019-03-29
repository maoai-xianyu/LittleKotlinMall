package mall.kotlin.com.goodscenter.injection.component

import dagger.Component
import mall.kotlin.com.baselibrary.injection.PerComponentScope
import mall.kotlin.com.baselibrary.injection.component.ActivityComponent
import mall.kotlin.com.goodscenter.injection.module.CartModule
import mall.kotlin.com.goodscenter.ui.fragment.CartFragment

/**
 * author:  zhangkun .
 * date:    on 2018/11/2.
 */
@PerComponentScope
@Component(
        dependencies = [ActivityComponent::class],
        modules = [CartModule::class]
)
interface CartComponent {
    fun inject(fragment: CartFragment)
}