package mall.kotlin.com.baselibrary.common

import android.app.Activity
import java.util.*

/**
 * author:  zhangkun .
 * date:    on 2018/11/6.
 * 单例 activity 管理栈
 */
class AppManager private constructor() {

    private val activityStack: Stack<Activity> = Stack()

    companion object {
        val instance: AppManager by lazy {
            AppManager()
        }
    }

    /**
     *  进栈
     */
    fun addActivity(activity: Activity) {
        activityStack.add(activity)
    }

    /**
     * 出栈 移除activity
     */
    fun finishActivity(activity: Activity) {
        activity.finish()
        activityStack.remove(activity)
    }

    /**
     * 获取当前栈顶
     */
    fun currentActivtiy(): Activity {
        return activityStack.lastElement()
    }

    /**
     * 退出app清理栈
     */
    fun finishAllActivity(){
        for (activity in activityStack){
            activity.finish()
        }
        activityStack.clear()
    }

    fun exitApp(){
        finishAllActivity()
    }
}