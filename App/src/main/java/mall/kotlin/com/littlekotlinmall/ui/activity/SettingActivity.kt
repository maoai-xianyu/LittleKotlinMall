package mall.kotlin.com.littlekotlinmall.ui.activity

import android.annotation.SuppressLint
import kotlinx.android.synthetic.main.activity_setting.*
import mall.kotlin.com.baselibrary.ext.onClick
import mall.kotlin.com.baselibrary.ui.activity.BaseUIActivity
import mall.kotlin.com.littlekotlinmall.R
import mall.kotlin.com.usercenter.utils.UserPrefsUtils
import org.jetbrains.anko.toast

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

        mUserProtocolTv.onClick {
            toast("用户协议")
        }
        mFeedBackTv.onClick {
            toast("反馈意见")
        }

        mAboutTv.onClick {
            toast("关于")
        }

    }
}