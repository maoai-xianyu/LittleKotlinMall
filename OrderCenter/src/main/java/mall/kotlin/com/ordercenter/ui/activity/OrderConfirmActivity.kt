package mall.kotlin.com.ordercenter.ui.activity

import com.alibaba.android.arouter.facade.annotation.Route
import mall.kotlin.com.baselibrary.ui.activity.BaseUIActivity
import mall.kotlin.com.ordercenter.R

/**
 * author:    zhangkun .
 * date:    on 2019/4/2.
 */
@Route(path = "/orderCenter/confirm")
class OrderConfirmActivity : BaseUIActivity() {

    override fun setView(): Int {
        return R.layout.activity_order_confirm
    }
}