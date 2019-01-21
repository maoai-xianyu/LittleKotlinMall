package mall.kotlin.com.usercenter.presenter.view

import mall.kotlin.com.baselibrary.presenter.view.BaseView
import mall.kotlin.com.usercenter.data.protocol.UserInfo

/**
 * author:  zhangkun .
 * date:    on 2018/10/25.
 */
interface UserInfoView : BaseView {

    fun onGetUploadTokenResult(result:String)
    fun onEditUserResult(result:UserInfo)

}