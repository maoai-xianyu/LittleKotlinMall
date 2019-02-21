package mall.kotlin.com.littlekotlinmall.ui.activity

import android.annotation.SuppressLint
import android.support.v4.app.Fragment
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import kotlinx.android.synthetic.main.activity_main.*
import mall.kotlin.com.baselibrary.ui.activity.BaseUIActivity
import mall.kotlin.com.littlekotlinmall.R
import mall.kotlin.com.littlekotlinmall.ui.fragment.HomeFragment
import mall.kotlin.com.littlekotlinmall.ui.fragment.MeFragment
import java.util.*

class MainActivity : BaseUIActivity() {


    private val mStack = Stack<Fragment>()

    private val mHomeFragment by lazy { HomeFragment() }
    private val mCategoryFragment by lazy { HomeFragment() }
    private val mCartFragment by lazy { HomeFragment() }
    private val mMsgFragment by lazy { HomeFragment() }
    private val mMeFragment by lazy { MeFragment() }


    override fun setView(): Int {
        return R.layout.activity_main
    }

    @SuppressLint("CommitTransaction")
    override fun initView() {
        mBottomNavBar.checkMsgBadge(false)
        mBottomNavBar.checkCartBadge(20)

        /*  val transaction = supportFragmentManager.beginTransaction()
          transaction.replace(mContainer.id, HomeFragment())
          transaction.commit()
        initFragment()
        changeFragment(0)*/

    }

    override fun setListener() {
        mBottomNavBar.setTabSelectedListener(object : BottomNavigationBar.OnTabSelectedListener {
            override fun onTabReselected(position: Int) {
            }

            override fun onTabUnselected(position: Int) {
            }

            override fun onTabSelected(position: Int) {
                changeFragment(position)
            }
        })

    }

    private fun changeFragment(position: Int) {
        val transaction = supportFragmentManager.beginTransaction()
        for (fragment in mStack) {
            transaction.hide(fragment)
        }
        transaction.show(mStack[position])
        transaction.commitAllowingStateLoss()
    }

    override fun start() {
        initFragment()
        changeFragment(0)
    }

    private fun initFragment() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(mContainer.id, mHomeFragment)
        transaction.add(mContainer.id, mCategoryFragment)
        transaction.add(mContainer.id, mCartFragment)
        transaction.add(mContainer.id, mMsgFragment)
        transaction.add(mContainer.id, mMeFragment)
        transaction.commitAllowingStateLoss()
        mStack.add(mHomeFragment)
        mStack.add(mCategoryFragment)
        mStack.add(mCartFragment)
        mStack.add(mMsgFragment)
        mStack.add(mMeFragment)
    }

}
