package mall.kotlin.com.baselibrary.injection.module

import android.content.Context
import dagger.Module
import dagger.Provides
import mall.kotlin.com.baselibrary.BaseApplication
import javax.inject.Singleton

/**
 * author:  zhangkun .
 * date:    on 2018/11/2.
 */
@Module
class AppModule(private val context: BaseApplication) {

    @Provides
    @Singleton
    fun providesContext(): Context {
        return context
    }
}