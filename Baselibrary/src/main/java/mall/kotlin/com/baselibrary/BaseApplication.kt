package mall.kotlin.com.baselibrary

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.alibaba.android.arouter.launcher.ARouter
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import mall.kotlin.com.baselibrary.injection.component.AppComponent
import mall.kotlin.com.baselibrary.injection.component.DaggerAppComponent
import mall.kotlin.com.baselibrary.injection.module.AppModule
import timber.log.Timber


/**
 * author:  zhangkun .
 * date:    on 2018/11/2.
 */
open class BaseApplication : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        initAppInjection()
        initLog()
        context = this
        initArouter()
    }

    private fun initAppInjection() {
        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this)).build()
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }

    private fun initLog() {
        Timber.plant(Timber.DebugTree())
        Logger.addLogAdapter(AndroidLogAdapter())
    }

    private fun initArouter() {
        if (BuildConfig.DEBUG) {
            ARouter.openLog()     // Print log
            ARouter.openDebug()   // Turn on debugging mode (If you are running in InstantRun mode, you must turn on debug mode! Online version needs to be closed, otherwise there is a security risk)
        }
        ARouter.init(this)
    }

}