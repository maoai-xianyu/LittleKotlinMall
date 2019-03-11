package mall.kotlin.com.usercenter.ui.activity

import android.view.View
import kotlinx.android.synthetic.main.activity_register.*
import mall.kotlin.com.baselibrary.common.AppManager
import mall.kotlin.com.baselibrary.ext.enable
import mall.kotlin.com.baselibrary.ui.activity.BaseMvpActivity
import mall.kotlin.com.usercenter.R
import mall.kotlin.com.usercenter.injection.component.DaggerUserComponent
import mall.kotlin.com.usercenter.injection.module.UserModule
import mall.kotlin.com.usercenter.presenter.RegisterPresenter
import mall.kotlin.com.usercenter.presenter.view.RegisterView
import org.jetbrains.anko.toast

class RegisterActivity : BaseMvpActivity<RegisterPresenter>(), RegisterView, View.OnClickListener {
    private var pressTime: Long = 0


    override fun setView(): Int {
        return  R.layout.activity_register
    }


    override fun setListener() {
        // 传统意义的定义
        /* mRegisterBtn.setOnClickListener {
             mPresenter.register(mMobileEt.text.toString(), mPwdEt.text.toString(), mVerifyCodeEt.text.toString())
         }*/
        //扩展函数
        /*mRegisterBtn.onClick {
            mPresenter.register(mMobileEt.text.toString(), mPwdEt.text.toString(), mVerifyCodeEt.text.toString())
        }
        mVerifyCodeBtn.onClick {
            // mPresenter.registerNamed(mMobileEt.text.toString(), mPwdEt.text.toString(), mVerifyCodeEt.text.toString())
            mVerifyCodeBtn.requestSendVerifyNumber()
        }*/
        //-------------------上面的代码进行简化换成原始的点击事件
        mVerifyCodeBtn.setOnClickListener(this)
        mRegisterBtn.setOnClickListener(this)

        // 扩展 EditText 文本监听
        /*mMobileEt.addTextChangedListener(object : DefaultTextWatcher(){
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                super.onTextChanged(p0, p1, p2, p3)
            }
        })*/
        mRegisterBtn.enable(mMobileEt) {isBtnEnable()}
        mRegisterBtn.enable(mVerifyCodeEt) {isBtnEnable()}
        mRegisterBtn.enable(mPwdEt) {isBtnEnable()}
        mRegisterBtn.enable(mPwdConfirmEt) {isBtnEnable()}
    }

    override fun injectComponent() {
        DaggerUserComponent.builder()
                .activityComponent(activityComponent)
                .userModule(UserModule())
                .build().inject(this)
        mPresenter.mView = this
    }

    override fun onRegisterResult(result: String) {
        toast(result)
        finish()
        //startActivity(intentFor<TestActivity>("id" to 5))
    }

    override fun onBackPressed() {
        val time = System.currentTimeMillis()
        if (time - pressTime > 2000) {
            toast("再按一次退出程序")
            pressTime = time
        } else {
            AppManager.instance.exitApp(this)
        }
    }

    override fun onClick(view: View) {
        when(view.id){
            R.id.mVerifyCodeBtn ->{
                mVerifyCodeBtn.requestSendVerifyNumber()
                toast("发送验证码成功")
            }

            R.id.mRegisterBtn ->{
                mPresenter.register(mMobileEt.text.toString(), mPwdEt.text.toString(), mVerifyCodeEt.text.toString())
            }
        }
    }

    // 用于判断注册按钮是否能点击
    private fun isBtnEnable():Boolean{
        return mMobileEt.text.isNullOrEmpty().not() and
         mVerifyCodeEt.text.isNullOrEmpty().not() and
         mPwdEt.text.isNullOrEmpty().not() and
         mPwdConfirmEt.text.isNullOrEmpty().not()
    }

}
