package mall.kotlin.com.usercenter.presenter

import mall.kotlin.com.baselibrary.presenter.BasePresenter
import mall.kotlin.com.usercenter.presenter.view.UserInfoView
import mall.kotlin.com.usercenter.service.UserService
import javax.inject.Inject

/**
 * author:  zhangkun .
 * date:    on 2018/10/25.
 */
class UserInfoPresenter @Inject constructor() : BasePresenter<UserInfoView>() {

    @Inject
    lateinit var userService: UserService


}