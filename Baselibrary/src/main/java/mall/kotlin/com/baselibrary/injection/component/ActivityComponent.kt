package mall.kotlin.com.baselibrary.injection.component

import android.app.Activity
import dagger.Component
import mall.kotlin.com.baselibrary.injection.ActivityScope
import mall.kotlin.com.baselibrary.injection.module.ActivityModule

/**
 * author:  zhangkun .
 * date:    on 2018/11/2.
 */
@ActivityScope
@Component(
        dependencies = [AppComponent::class],
        modules = [ActivityModule::class]
)
interface ActivityComponent {
    fun activity(): Activity
}