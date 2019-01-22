package mall.kotlin.com.usercenter.ui.activity

import android.view.View
import kotlinx.android.synthetic.main.activity_forget_pwd.*
import mall.kotlin.com.baselibrary.ext.enable
import mall.kotlin.com.baselibrary.ext.onClick
import mall.kotlin.com.baselibrary.ui.activity.BaseMvpActivity
import mall.kotlin.com.usercenter.R
import mall.kotlin.com.usercenter.injection.component.DaggerUserComponent
import mall.kotlin.com.usercenter.injection.module.UserModule
import mall.kotlin.com.usercenter.presenter.ForgetPwdPresenter
import mall.kotlin.com.usercenter.presenter.view.ForgetPwdView
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

/**
 * 忘记密码
 */

class ForgetPwdPwdActivity : BaseMvpActivity<ForgetPwdPresenter>(), ForgetPwdView, View.OnClickListener {


    override fun setView(): Int {
        return R.layout.activity_forget_pwd
    }


    override fun setListener() {

        mVerifyCodeBtn.onClick(this)
        mNextBtn.onClick(this)

        mNextBtn.enable(mMobileEt) { isBtnEnable() }
        mNextBtn.enable(mVerifyCodeEt) { isBtnEnable() }
    }

    override fun injectComponent() {
        DaggerUserComponent.builder()
                .activityComponent(activityComponent)
                .userModule(UserModule())
                .build().inject(this)
        mPresenter.mView = this
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.mVerifyCodeBtn -> {
                mVerifyCodeBtn.requestSendVerifyNumber()
                toast("发送验证码成功")
            }

            R.id.mNextBtn -> {
                mPresenter.forgetPwd(mMobileEt.text.toString(), mVerifyCodeEt.text.toString())
            }
        }
    }

    // 用于判断注册按钮是否能点击
    private fun isBtnEnable(): Boolean {
        return mMobileEt.text.isNullOrEmpty().not() and
                mVerifyCodeEt.text.isNullOrEmpty().not()
    }

    override fun onForgetPwdResult(result: String) {
        toast(result)
        startActivity<ResetPwdActivity>("mobile" to mMobileEt.text.toString())
    }

}
