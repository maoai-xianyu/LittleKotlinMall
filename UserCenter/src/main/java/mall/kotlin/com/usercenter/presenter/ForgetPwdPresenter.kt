package mall.kotlin.com.usercenter.presenter

import mall.kotlin.com.baselibrary.ext.execute
import mall.kotlin.com.baselibrary.presenter.BasePresenter
import mall.kotlin.com.baselibrary.rx.BaseSubscriber
import mall.kotlin.com.usercenter.presenter.view.ForgetPwdView
import mall.kotlin.com.usercenter.service.UserService
import javax.inject.Inject

/**
 * author:  zhangkun .
 * date:    on 2018/10/25.
 */
class ForgetPwdPresenter @Inject constructor() : BasePresenter<ForgetPwdView>() {

    @Inject
    lateinit var userService: UserService

    fun forgetPwd(mobile: String, verifyCode: String) {

        /**
         * 业务逻辑
         */
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        userService.forgetPwd(mobile, verifyCode)
                .execute(object : BaseSubscriber<Boolean>(mView) {
                    override fun onNext(t: Boolean) {
                        if (t)
                            mView.onForgetPwdResult("验证成功")
                        else
                            mView.onForgetPwdResult("验证失败")
                    }
                }, lifecycleProvider)
    }

}