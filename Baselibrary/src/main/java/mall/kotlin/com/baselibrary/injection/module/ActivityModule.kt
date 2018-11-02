package mall.kotlin.com.baselibrary.injection.module

import android.app.Activity
import dagger.Module
import dagger.Provides

/**
 * author:  zhangkun .
 * date:    on 2018/11/2.
 */
@Module
class ActivityModule(private val activity: Activity) {

    @Provides
    fun providesActivity(): Activity {
        return activity
    }
}