package mall.kotlin.com.ordercenter.ui.activity

import kotlinx.android.synthetic.main.activity_address.*
import mall.kotlin.com.baselibrary.ext.onClick
import mall.kotlin.com.baselibrary.ui.activity.BaseUIActivity
import mall.kotlin.com.ordercenter.R
import org.jetbrains.anko.startActivity

/**
 * author:    zhangkun .
 * date:    on 2019/4/8.
 */
class ShipAddressActivity : BaseUIActivity() {

    override fun setView(): Int {
        return R.layout.activity_address
    }

    override fun initView() {
        mAddAddressBtn.onClick {

            startActivity<ShipAddressEditActivity>()

        }
    }


}