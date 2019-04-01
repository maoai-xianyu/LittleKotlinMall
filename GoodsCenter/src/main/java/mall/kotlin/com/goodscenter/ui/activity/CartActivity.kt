package mall.kotlin.com.goodscenter.ui.activity

import mall.kotlin.com.baselibrary.ui.activity.BaseUIActivity
import mall.kotlin.com.goodscenter.R
import mall.kotlin.com.goodscenter.ui.fragment.CartFragment

/**
 * author:  zhangkun .
 * date:    on 2019/3/12.
 */
class CartActivity : BaseUIActivity() {


    override fun setView(): Int {
        return R.layout.activity_cart
    }

    override fun initView() {
        val fragment = supportFragmentManager.findFragmentById(R.id.fragment)
        (fragment as CartFragment).setBackVisible(true)

    }

}