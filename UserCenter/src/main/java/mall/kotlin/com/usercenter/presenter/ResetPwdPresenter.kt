package mall.kotlin.com.usercenter.presenter

import mall.kotlin.com.baselibrary.ext.execute
import mall.kotlin.com.baselibrary.presenter.BasePresenter
import mall.kotlin.com.baselibrary.rx.BaseSubscriber
import mall.kotlin.com.usercenter.presenter.view.ResetPwdView
import mall.kotlin.com.usercenter.service.UserService
import javax.inject.Inject

/**
 * author:  zhangkun .
 * date:    on 2018/10/25.
 */
class ResetPwdPresenter @Inject constructor() : BasePresenter<ResetPwdView>() {

    @Inject
    lateinit var userService: UserService

    fun resetPwd(mobile: String, pwd: String) {

        /**
         * 业务逻辑
         */
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        userService.resetPwd(mobile, pwd)
                .execute(object : BaseSubscriber<Boolean>(mView) {
                    override fun onNext(t: Boolean) {
                        if (t)
                            mView.onResetPwdResult("重置密码成功")
                        else
                            mView.onResetPwdResult("重置密码失败")
                    }
                }, lifecycleProvider)
    }

}