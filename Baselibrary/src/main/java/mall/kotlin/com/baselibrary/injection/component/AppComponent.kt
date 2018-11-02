package mall.kotlin.com.baselibrary.injection.component

import android.content.Context
import dagger.Component
import mall.kotlin.com.baselibrary.injection.module.AppModule
import javax.inject.Singleton

/**
 * author:  zhangkun .
 * date:    on 2018/11/2.
 */
@Singleton
@Component(
        modules = [AppModule::class]
)
interface AppComponent {
    fun context():Context
}