package mall.kotlin.com.usercenter.presenter

import mall.kotlin.com.baselibrary.ext.execute
import mall.kotlin.com.baselibrary.presenter.BasePresenter
import mall.kotlin.com.baselibrary.rx.BaseSubscriber
import mall.kotlin.com.usercenter.data.protocol.UserInfo
import mall.kotlin.com.usercenter.presenter.view.UserInfoView
import mall.kotlin.com.usercenter.service.UploadService
import mall.kotlin.com.usercenter.service.UserService
import javax.inject.Inject

/**
 * author:  zhangkun .
 * date:    on 2018/10/25.
 */
class UserInfoPresenter @Inject constructor() : BasePresenter<UserInfoView>() {

    @Inject
    lateinit var userService: UserService

    @Inject
    lateinit var uploadService: UploadService

    fun getUploadToken() {
        if (!checkNetWork()) return
        mView.showLoading()
        uploadService.getUploadToken().execute(object : BaseSubscriber<String>(mView) {
            override fun onNext(t: String) {
                mView.onGetUploadTokenResult(t)
            }

        }, lifecycleProvider)
    }


    fun editUser(userIcon: String, userName: String, userGender: String, userSign: String) {
        if (!checkNetWork()) return
        mView.showLoading()
        userService.editUser(userIcon, userName, userGender, userSign)
                .execute(object : BaseSubscriber<UserInfo>(mView) {
                    override fun onNext(t: UserInfo) {
                        mView.onEditUserResult(t)
                    }
                }, lifecycleProvider)
    }


}