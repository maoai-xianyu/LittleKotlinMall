package mall.kotlin.com.littlekotlinmall.ui.fragment

import android.view.View
import kotlinx.android.synthetic.main.fragment_me.*
import mall.kotlin.com.baselibrary.ext.loadUrl
import mall.kotlin.com.baselibrary.ext.onClick
import mall.kotlin.com.baselibrary.ui.fragment.BaseUIFragment
import mall.kotlin.com.baselibrary.utils.AppPrefsUtils
import mall.kotlin.com.littlekotlinmall.R
import mall.kotlin.com.littlekotlinmall.ui.activity.SettingActivity
import mall.kotlin.com.ordercenter.common.OrderConstant
import mall.kotlin.com.ordercenter.common.OrderStatus
import mall.kotlin.com.ordercenter.ui.activity.OrderActivity
import mall.kotlin.com.ordercenter.ui.activity.ShipAddressActivity
import mall.kotlin.com.provider.common.ProviderConstant
import mall.kotlin.com.provider.common.afterLogin
import mall.kotlin.com.provider.common.isLogined
import mall.kotlin.com.usercenter.ui.activity.LoginActivity
import mall.kotlin.com.usercenter.ui.activity.UserInfoActivity
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast
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
        mAddressTv.onClick(this)
        mWaitPayOrderTv.onClick(this)
        mWaitConfirmOrderTv.onClick(this)
        mCompleteOrderTv.onClick(this)
        mShareTv.onClick(this)
        mAllOrderTv.onClick(this)

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

            R.id.mAddressTv -> {
                afterLogin {
                    startActivity<ShipAddressActivity>()
                }
            }


            R.id.mWaitPayOrderTv -> {
                afterLogin {
                    startActivity<OrderActivity>(OrderConstant.KEY_ORDER_STATUS to OrderStatus.ORDER_WAIT_PAY)
                }
            }

            R.id.mWaitConfirmOrderTv -> {
                afterLogin {
                    startActivity<OrderActivity>(OrderConstant.KEY_ORDER_STATUS to OrderStatus.ORDER_WAIT_CONFIRM)
                }
            }

            R.id.mCompleteOrderTv -> {
                afterLogin {
                    startActivity<OrderActivity>(OrderConstant.KEY_ORDER_STATUS to OrderStatus.ORDER_COMPLETED)
                }
            }

            R.id.mShareTv -> {
                toast(R.string.coming_soon_tip)
            }

            R.id.mAllOrderTv -> {
                afterLogin {
                    startActivity<OrderActivity>()
                }
            }
        }

    }

}