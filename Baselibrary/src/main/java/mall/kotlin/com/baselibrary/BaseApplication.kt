package mall.kotlin.com.baselibrary

import android.app.Application
import mall.kotlin.com.baselibrary.injection.component.AppComponent
import mall.kotlin.com.baselibrary.injection.component.DaggerAppComponent
import mall.kotlin.com.baselibrary.injection.module.AppModule

/**
 * author:  zhangkun .
 * date:    on 2018/11/2.
 */
class BaseApplication : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        initAppInjection()
    }

    private fun initAppInjection() {

        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this)).build()


    }
}