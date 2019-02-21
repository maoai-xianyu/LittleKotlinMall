package mall.kotlin.com.littlekotlinmall.ui.fragment

import android.view.View
import kotlinx.android.synthetic.main.fragment_me.*
import mall.kotlin.com.baselibrary.ext.loadUrl
import mall.kotlin.com.baselibrary.ext.onClick
import mall.kotlin.com.baselibrary.ui.fragment.BaseUIFragment
import mall.kotlin.com.baselibrary.utils.AppPrefsUtils
import mall.kotlin.com.littlekotlinmall.R
import mall.kotlin.com.littlekotlinmall.ui.activity.SettingActivity
import mall.kotlin.com.provider.common.ProviderConstant
import mall.kotlin.com.provider.common.isLogined
import mall.kotlin.com.usercenter.ui.activity.LoginActivity
import mall.kotlin.com.usercenter.ui.activity.UserInfoActivity
import org.jetbrains.anko.support.v4.startActivity
import timber.log.Timber


/**
 * author:  zhangkun .
 * date:    on 2019/1/22.
 */
class MeFragment : BaseUIFragment(), View.OnClickListener {
    override fun setView(): Int {
        return R.layout.fragment_me
    }

    override fun initView() {

    }

    override fun setListener() {
        mUserIconIv.onClick(this)
        mUserNameTv.onClick(this)
        mSettingTv.onClick(this)
    }


    override fun onStart() {
        super.onStart()
        loadData()
    }

    private fun loadData() {
        Timber.d("进行加载数据 onStart")
        if (isLogined()) {
            // 登录
            val userIcon = AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_ICON)
            if (userIcon.isNotEmpty()) {
                mUserIconIv.loadUrl(userIcon)
            }
            mUserNameTv.text = AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_NAME)
        } else {
            // 非登录
            mUserIconIv.setImageResource(R.drawable.icon_default_user)
            mUserNameTv.text = getString(R.string.un_login_text)
        }
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.mUserIconIv, R.id.mUserNameTv -> {
                if (isLogined()) {
                    startActivity<UserInfoActivity>()
                } else {
                    startActivity<LoginActivity>()
                }
            }
            R.id.mSettingTv -> {
                startActivity<SettingActivity>()
            }
        }

    }

}