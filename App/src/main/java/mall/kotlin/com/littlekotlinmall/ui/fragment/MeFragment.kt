package mall.kotlin.com.littlekotlinmall.ui.fragment

import kotlinx.android.synthetic.main.fragment_me.*
import mall.kotlin.com.baselibrary.common.BaseConstant
import mall.kotlin.com.baselibrary.ext.loadUrl
import mall.kotlin.com.baselibrary.ui.fragment.BaseUIFragment
import mall.kotlin.com.baselibrary.utils.AppPrefsUtils
import mall.kotlin.com.provider.common.ProviderConstant
import timber.log.Timber


/**
 * author:  zhangkun .
 * date:    on 2019/1/22.
 */
class MeFragment : BaseUIFragment() {
    override fun setView(): Int {
        return mall.kotlin.com.littlekotlinmall.R.layout.fragment_me
    }

    override fun initView() {

    }

    override fun onStart() {
        super.onStart()
        loadData()
    }

    private fun loadData() {
        Timber.d("进行加载数据 onStart")
        if (AppPrefsUtils.getString(BaseConstant.KEY_SP_TOKEN) == "") {
            // 非登录

        } else {
            // 登录
            val userIcon = AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_ICON)
            if (userIcon.isNotEmpty()) {
                mUserIconIv.loadUrl(userIcon)
            }
            mUserNameTv.text = AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_NAME)

        }
    }

}