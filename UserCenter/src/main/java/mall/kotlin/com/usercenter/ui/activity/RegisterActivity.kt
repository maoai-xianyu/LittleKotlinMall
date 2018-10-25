package mall.kotlin.com.usercenter.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_register.*
import mall.kotlin.com.usercenter.R
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        // 传统意义的定义
        mRegisterBtn.setOnClickListener {
            //Toast.makeText(this,"注册",Toast.LENGTH_LONG).s)
            toast("注册成功")
            //startActivity(intentFor<TestActivity>("id" to 5))
            startActivity<TestActivity>("id" to 10)

        }
    }
}
