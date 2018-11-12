package mall.kotlin.com.usercenter.ui.activity

import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_register.*
import mall.kotlin.com.baselibrary.common.AppManager
import mall.kotlin.com.baselibrary.ext.onClick
import mall.kotlin.com.baselibrary.ui.activity.BaseMvpActivity
import mall.kotlin.com.baselibrary.widgets.VerifyButton
import mall.kotlin.com.usercenter.R
import mall.kotlin.com.usercenter.injection.component.DaggerUserComponent
import mall.kotlin.com.usercenter.injection.module.UserModule
import mall.kotlin.com.usercenter.presenter.RegisterPresenter
import mall.kotlin.com.usercenter.presenter.view.RegisterView
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class RegisterActivity : BaseMvpActivity<RegisterPresenter>(), RegisterView {

    private var pressTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // 传统意义的定义
        /* mRegisterBtn.setOnClickListener {
             mPresenter.register(mMobileEt.text.toString(), mPwdEt.text.toString(), mVerifyCodeEt.text.toString())
         }*/

        //扩展函数
        mRegisterBtn.onClick {
            mPresenter.register(mMobileEt.text.toString(), mPwdEt.text.toString(), mVerifyCodeEt.text.toString())
        }

       /* mVerifyCodeBtm.onClick(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                mPresenter.registerNamed(mMobileEt.text.toString(), mPwdEt.text.toString(), mVerifyCodeEt.text.toString())
            }
        })*/

        mVerifyCodeBtm.onClick {
            mVerifyCodeBtm.requestSendVerifyNumber()
        }

        // 目前没有什么用
        mVerifyCodeBtm.setOnVerifyBtnClick(object : VerifyButton.OnVerifyBtnClick{
            override fun onClick() {
            }
        })
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
        //startActivity(intentFor<TestActivity>("id" to 5))
        if (result == "注册成功")
            startActivity<TestActivity>("id" to 10)
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
}
