package mall.kotlin.com.usercenter.presenter

import mall.kotlin.com.baselibrary.presenter.BasePresenter
import mall.kotlin.com.usercenter.presenter.view.RegisterView

/**
 * author:  zhangkun .
 * date:    on 2018/10/25.
 */
class RegisterPresenter : BasePresenter<RegisterView>() {


    fun register(mobile: String, verifyCode: String) {

        /**
         * 业务逻辑
         */
        mView.onRegisterResult(true)
    }
}