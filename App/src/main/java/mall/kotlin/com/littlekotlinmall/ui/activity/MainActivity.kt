package mall.kotlin.com.littlekotlinmall.ui.activity

import android.annotation.SuppressLint
import kotlinx.android.synthetic.main.activity_main.*
import mall.kotlin.com.baselibrary.ui.activity.BaseUIActivity
import mall.kotlin.com.littlekotlinmall.R
import mall.kotlin.com.littlekotlinmall.ui.fragment.HomeFragment

class MainActivity : BaseUIActivity() {

    override fun setView(): Int {
        return R.layout.activity_main
    }

    @SuppressLint("CommitTransaction")
    override fun initView() {
        mBottomNavBar.checkMsgBadge(false)
        mBottomNavBar.checkCartBadge(20)

        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(mContainer.id, HomeFragment())
        transaction.commit()

    }
}
