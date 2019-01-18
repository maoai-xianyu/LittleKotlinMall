package mall.kotlin.com.usercenter.ui.activity

import android.os.Bundle
import kotlinx.android.synthetic.main.activity_reset_pwd.*
import mall.kotlin.com.baselibrary.ext.enable
import mall.kotlin.com.baselibrary.ext.onClick
import mall.kotlin.com.baselibrary.ui.activity.BaseMvpActivity
import mall.kotlin.com.usercenter.R
import mall.kotlin.com.usercenter.injection.component.DaggerUserComponent
import mall.kotlin.com.usercenter.injection.module.UserModule
import mall.kotlin.com.usercenter.presenter.ResetPwdPresenter
import mall.kotlin.com.usercenter.presenter.view.ResetPwdView
import org.jetbrains.anko.clearTop
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.singleTop
import org.jetbrains.anko.toast

/**
 * 重置密码
 */

class ResetPwdActivity : BaseMvpActivity<ResetPwdPresenter>(), ResetPwdView {

    private var mobile: String = ""

    override fun getArgs(bundle: Bundle?) {
        if (bundle != null) {
            mobile = bundle.getString("mobile", "")
        }

    }

    override fun setView(): Int {
        return R.layout.activity_reset_pwd
    }

    override fun initView() {

    }

    override fun setListener() {

        mConfirmBtn.onClick {
            if (mPwdEt.text.toString() != mPwdConfirmEt.text.toString()) {
                toast("密码不一致")
                return@onClick
            }
            mPresenter.resetPwd(mobile, mPwdConfirmEt.text.toString())

        }

        mConfirmBtn.enable(mPwdEt) { isBtnEnable() }
        mConfirmBtn.enable(mPwdConfirmEt) { isBtnEnable() }
    }

    override fun injectComponent() {
        DaggerUserComponent.builder()
                .activityComponent(activityComponent)
                .userModule(UserModule())
                .build().inject(this)
        mPresenter.mView = this
    }


    // 用于判断注册按钮是否能点击
    private fun isBtnEnable(): Boolean {
        return mPwdEt.text.isNullOrEmpty().not() and
                mPwdConfirmEt.text.isNullOrEmpty().not()
    }


    override fun onResetPwdResult(result: String) {
        toast(result)
        startActivity(intentFor<LoginActivity>().singleTop().clearTop())

    }
}
