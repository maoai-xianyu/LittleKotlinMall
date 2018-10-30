package mall.kotlin.com.usercenter.presenter

import mall.kotlin.com.baselibrary.ext.execute
import mall.kotlin.com.baselibrary.presenter.BasePresenter
import mall.kotlin.com.baselibrary.rx.BaseSubscriber
import mall.kotlin.com.usercenter.presenter.view.RegisterView
import mall.kotlin.com.usercenter.service.impl.UserServiceImpl

/**
 * author:  zhangkun .
 * date:    on 2018/10/25.
 */
class RegisterPresenter : BasePresenter<RegisterView>() {


    fun register(mobile: String, pwd: String,verifyCode: String) {

        /**
         * 业务逻辑
         */
        val userService = UserServiceImpl()
        userService.register(mobile, pwd,verifyCode)
                .execute(object : BaseSubscriber<Boolean>() {
                    override fun onNext(t: Boolean) {
                        mView.onRegisterResult(t)
                    }
                })
    }
}