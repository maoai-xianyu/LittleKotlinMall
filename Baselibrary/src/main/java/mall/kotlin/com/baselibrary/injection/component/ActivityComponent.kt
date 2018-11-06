package mall.kotlin.com.baselibrary.injection.component

import android.app.Activity
import android.content.Context
import com.trello.rxlifecycle.LifecycleProvider
import dagger.Component
import mall.kotlin.com.baselibrary.injection.ActivityScope
import mall.kotlin.com.baselibrary.injection.module.ActivityModule
import mall.kotlin.com.baselibrary.injection.module.LifecycleProviderModule

/**
 * author:  zhangkun .
 * date:    on 2018/11/2.
 */
@ActivityScope
@Component(
        dependencies = [AppComponent::class],
        modules = [ActivityModule::class, LifecycleProviderModule::class]
)
interface ActivityComponent {
    fun activity(): Activity
    fun context(): Context
    fun lifecycleProvider(): LifecycleProvider<*>
}