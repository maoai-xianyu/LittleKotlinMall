package mall.kotlin.com.usercenter.presenter

import mall.kotlin.com.baselibrary.ext.execute
import mall.kotlin.com.baselibrary.presenter.BasePresenter
import mall.kotlin.com.baselibrary.rx.BaseSubscriber
import mall.kotlin.com.usercenter.presenter.view.RegisterView
import mall.kotlin.com.usercenter.service.UserService
import javax.inject.Inject
import javax.inject.Named

/**
 * author:  zhangkun .
 * date:    on 2018/10/25.
 */
class RegisterPresenter @Inject constructor() : BasePresenter<RegisterView>() {

    @Inject
    //@Named("service")  //写法错误，这个是在java中写的，kotlin不这样写,会报错
    // mall.kotlin.com.usercenter.service.UserService cannot be provided without an @Provides-annotated method.
    //    public abstract void inject(@org.jetbrains.annotations.NotNull()
    @field:[Named("service")]
    lateinit var userService: UserService

    @Inject
    //@Named("serviceNamed")
    @field:[Named("serviceNamed")]
    lateinit var userServiceNamed: UserService

    fun register(mobile: String, pwd: String, verifyCode: String) {

        /**
         * 业务逻辑
         */
        if (!checkNetWork()){
            println("没有网络")
            return
        }
        mView.showLoading()
        userService.register(mobile, pwd, verifyCode)
                .execute(object : BaseSubscriber<Boolean>(mView) {
                    override fun onNext(t: Boolean) {
                        if (t)
                            mView.onRegisterResult("注册成功")
                        else
                            mView.onRegisterResult("注册失败")
                    }
                }, lifecycleProvider)
    }


    fun registerNamed(mobile: String, pwd: String, verifyCode: String) {

        /**
         * 业务逻辑
         */
        mView.showLoading()
        userServiceNamed.register(mobile, pwd, verifyCode)
                .execute(object : BaseSubscriber<Boolean>(mView) {
                    override fun onNext(t: Boolean) {
                        if (t)
                            mView.onRegisterResult("注册成功")
                        else
                            mView.onRegisterResult("注册失败")

                    }
                }, lifecycleProvider)
    }
}