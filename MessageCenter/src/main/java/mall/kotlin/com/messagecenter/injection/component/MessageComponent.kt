package mall.kotlin.com.messagecenter.injection.component

import dagger.Component
import mall.kotlin.com.baselibrary.injection.PerComponentScope
import mall.kotlin.com.baselibrary.injection.component.ActivityComponent
import mall.kotlin.com.messagecenter.injection.module.MessageModule
import mall.kotlin.com.messagecenter.ui.fragment.MessageFragment

/**
 * author:    zhangkun .
 * date:    on 2019-05-10.
 */

@PerComponentScope
@Component(
        dependencies = [ActivityComponent::class],
        modules = [MessageModule::class]
)
interface MessageComponent {

    fun inject(fragment: MessageFragment)
}