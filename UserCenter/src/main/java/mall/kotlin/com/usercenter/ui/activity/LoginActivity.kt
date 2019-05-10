package mall.kotlin.com.usercenter.ui.activity

import android.view.View
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import kotlinx.android.synthetic.main.activity_login.*
import mall.kotlin.com.baselibrary.ext.enable
import mall.kotlin.com.baselibrary.ext.onClick
import mall.kotlin.com.baselibrary.ui.activity.BaseMvpActivity
import mall.kotlin.com.provider.PushProvider
import mall.kotlin.com.provider.router.RouterPath
import mall.kotlin.com.usercenter.R
import mall.kotlin.com.usercenter.data.protocol.UserInfo
import mall.kotlin.com.usercenter.injection.component.DaggerUserComponent
import mall.kotlin.com.usercenter.injection.module.UserModule
import mall.kotlin.com.usercenter.presenter.LoginPresenter
import mall.kotlin.com.usercenter.presenter.view.LoginView
import mall.kotlin.com.usercenter.utils.UserPrefsUtils
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

@Route(path = RouterPath.UserCenter.PATH_LOGIN)
class LoginActivity : BaseMvpActivity<LoginPresenter>(), LoginView, View.OnClickListener {


    @Autowired(name = RouterPath.MessageCenter.PATH_MESSAGE_PUSH)
    @JvmField
    var mPushProvider: PushProvider? = null

    override fun setView(): Int {
        return R.layout.activity_login
    }

    override fun setListener() {

        mLoginBtn.onClick(this)
        mForgetPwdTv.onClick(this)
        mLoginBtn.enable(mMobileEt) { isBtnEnable() }
        mLoginBtn.enable(mPwdEt) { isBtnEnable() }
        mHeaderBar.getRightView().onClick(this)
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
            R.id.mRightTv -> {
                startActivity<RegisterActivity>()
            }

            R.id.mLoginBtn -> {
                mPresenter.login(mMobileEt.text.toString(), mPwdEt.text.toString(), mPushProvider?.getPushId()
                        ?: "")
            }

            R.id.mForgetPwdTv -> {
                startActivity<ForgetPwdPwdActivity>()
            }
        }
    }

    // 用于判断注册按钮是否能点击
    private fun isBtnEnable(): Boolean {
        return mMobileEt.text.isNullOrEmpty().not() and
                mPwdEt.text.isNullOrEmpty().not()
    }

    override fun onLoginResult(result: UserInfo) {
        toast("登录成功")
        // 存储
        UserPrefsUtils.putUserInfo(result)
        finish()
    }

}
