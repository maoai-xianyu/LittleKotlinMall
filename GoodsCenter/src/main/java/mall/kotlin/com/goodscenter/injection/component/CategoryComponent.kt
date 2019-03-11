package mall.kotlin.com.goodscenter.injection.component

import dagger.Component
import mall.kotlin.com.baselibrary.injection.PerComponentScope
import mall.kotlin.com.baselibrary.injection.component.ActivityComponent
import mall.kotlin.com.goodscenter.injection.module.CategoryModule
import mall.kotlin.com.goodscenter.ui.fragment.CategoryFragment

/**
 * author:  zhangkun .
 * date:    on 2018/11/2.
 */
@PerComponentScope
@Component(
        dependencies = [ActivityComponent::class],
        modules = [CategoryModule::class]
)
interface CategoryComponent {
    fun inject(fragment: CategoryFragment)
}