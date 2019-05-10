package mall.kotlin.com.littlekotlinmall.ui.activity

import android.annotation.SuppressLint
import android.support.v4.app.Fragment
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.eightbitlab.rxbus.Bus
import com.eightbitlab.rxbus.registerInBus
import kotlinx.android.synthetic.main.activity_main.*
import mall.kotlin.com.baselibrary.ui.activity.BaseUIActivity
import mall.kotlin.com.baselibrary.utils.AppPrefsUtils
import mall.kotlin.com.goodscenter.common.GoodsConstant
import mall.kotlin.com.goodscenter.event.UpdateCartSizeEvent
import mall.kotlin.com.goodscenter.ui.fragment.CartFragment
import mall.kotlin.com.goodscenter.ui.fragment.CategoryFragment
import mall.kotlin.com.littlekotlinmall.R
import mall.kotlin.com.littlekotlinmall.ui.fragment.HomeFragment
import mall.kotlin.com.littlekotlinmall.ui.fragment.MeFragment
import mall.kotlin.com.messagecenter.ui.fragment.MessageFragment
import mall.kotlin.com.provider.event.MessageBadgeEvent
import java.util.*

class MainActivity : BaseUIActivity() {


    //Fragment 栈管理
    private val mStack = Stack<Fragment>()

    private val mHomeFragment by lazy { HomeFragment() }
    private val mCategoryFragment by lazy { CategoryFragment() }
    private val mCartFragment by lazy { CartFragment() }
    private val mMsgFragment by lazy { MessageFragment() }
    private val mMeFragment by lazy { MeFragment() }


    override fun setView(): Int {
        return R.layout.activity_main
    }

    @SuppressLint("CommitTransaction")
    override fun initView() {
        mBottomNavBar.checkMsgBadge(false)

        /*  val transaction = supportFragmentManager.beginTransaction()
          transaction.replace(mContainer.id, HomeFragment())
          transaction.commit()
        initFragment()
        changeFragment(0)*/

        initFragment()
        changeFragment(0)
        initObserve()
        loadCartSize()
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

    private fun initObserve() {
        Bus.observe<UpdateCartSizeEvent>()
                .subscribe {
                    loadCartSize()
                }.registerInBus(this)

        Bus.observe<MessageBadgeEvent>()
                .subscribe { t: MessageBadgeEvent ->
                    run { mBottomNavBar.checkMsgBadge(t.isVisible) }
                }.registerInBus(this)


    }

    private fun loadCartSize() {
        mBottomNavBar.checkCartBadge(AppPrefsUtils.getInt(GoodsConstant.SP_CART_SIZE))

    }

    override fun onDestroy() {
        super.onDestroy()
        Bus.unregister(this)
    }

}
