package mall.kotlin.com.littlekotlinmall

import mall.kotlin.com.baselibrary.BaseApplication

/*
    主工程 Application
 */
class MainApplication: BaseApplication() {
    override fun onCreate() {
        super.onCreate()

        //极光推送初始化
        /*JPushInterface.setDebugMode(true)
        JPushInterface.init(this)*/
    }
}


