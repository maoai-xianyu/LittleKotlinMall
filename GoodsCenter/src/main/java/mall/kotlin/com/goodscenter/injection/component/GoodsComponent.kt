package mall.kotlin.com.goodscenter.injection.component

import dagger.Component
import mall.kotlin.com.baselibrary.injection.PerComponentScope
import mall.kotlin.com.baselibrary.injection.component.ActivityComponent
import mall.kotlin.com.goodscenter.injection.module.GoodsModule
import mall.kotlin.com.goodscenter.ui.activity.GoodsActivity
import mall.kotlin.com.goodscenter.ui.fragment.GoodsDetailTabOneFragment

/**
 * author:  zhangkun .
 * date:    on 2018/11/2.
 */
@PerComponentScope
@Component(
        dependencies = [ActivityComponent::class],
        modules = [GoodsModule::class]
)
interface GoodsComponent {
    fun inject(activity: GoodsActivity)
    fun inject(fragment: GoodsDetailTabOneFragment)
}