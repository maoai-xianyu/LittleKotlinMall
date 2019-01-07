package mall.kotlin.com.usercenter.ui.activity

import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_login.*
import mall.kotlin.com.baselibrary.ext.enable
import mall.kotlin.com.baselibrary.ui.activity.BaseMvpActivity
import mall.kotlin.com.usercenter.R
import mall.kotlin.com.usercenter.data.protocol.UserInfo
import mall.kotlin.com.usercenter.injection.component.DaggerUserComponent
import mall.kotlin.com.usercenter.injection.module.UserModule
import mall.kotlin.com.usercenter.presenter.LoginPresenter
import mall.kotlin.com.usercenter.presenter.view.LoginView

class LoginActivity : BaseMvpActivity<LoginPresenter>(), LoginView, View.OnClickListener {


    override fun getArgs(var1: Bundle?) {

    }
    override fun setView(): Int {
        return  R.layout.activity_login
    }

    override fun initView() {

    }

    override fun setListener() {

        mLoginBtn.setOnClickListener(this)

        mLoginBtn.enable(mMobileEt) {isBtnEnable()}
        mLoginBtn.enable(mPwdEt) {isBtnEnable()}
    }

    override fun injectComponent() {
        DaggerUserComponent.builder()
                .activityComponent(activityComponent)
                .userModule(UserModule())
                .build().inject(this)
        mPresenter.mView = this
    }



    override fun onClick(view: View) {
        when(view.id){
            R.id.mLoginBtn ->{
            }
        }
    }

    // 用于判断注册按钮是否能点击
    private fun isBtnEnable():Boolean{
        return mMobileEt.text.isNullOrEmpty().not() and
         mPwdEt.text.isNullOrEmpty().not()
    }

    override fun onLoginResult(result: UserInfo) {
    }

}
