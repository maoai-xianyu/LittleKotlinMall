package mall.kotlin.com.usercenter.ui.activity

import android.os.Bundle
import kotlinx.android.synthetic.main.activity_register.*
import mall.kotlin.com.baselibrary.ui.activity.BaseMvpActivity
import mall.kotlin.com.usercenter.R
import mall.kotlin.com.usercenter.presenter.RegisterPresenter
import mall.kotlin.com.usercenter.presenter.view.RegisterView
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class RegisterActivity : BaseMvpActivity<RegisterPresenter>(), RegisterView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        mPresenter = RegisterPresenter()
        mPresenter.mView = this
        // 传统意义的定义
        mRegisterBtn.setOnClickListener {
            mPresenter.register("","")
        }
    }

    override fun onRegisterResult(result: Boolean) {
        toast("注册成功")
        //startActivity(intentFor<TestActivity>("id" to 5))
        startActivity<TestActivity>("id" to 10)
    }
}
