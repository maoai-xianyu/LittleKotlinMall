package mall.kotlin.com.littlekotlinmall.ui.activity

import android.annotation.SuppressLint
import kotlinx.android.synthetic.main.activity_setting.*
import mall.kotlin.com.baselibrary.ext.onClick
import mall.kotlin.com.baselibrary.ui.activity.BaseUIActivity
import mall.kotlin.com.littlekotlinmall.R
import mall.kotlin.com.usercenter.utils.UserPrefsUtils

class SettingActivity : BaseUIActivity() {


    override fun setView(): Int {
        return R.layout.activity_setting
    }

    @SuppressLint("CommitTransaction")
    override fun initView() {

    }

    override fun setListener() {

        mLogoutBtn.onClick {
            UserPrefsUtils.putUserInfo(null)
            finish()
        }

    }
}