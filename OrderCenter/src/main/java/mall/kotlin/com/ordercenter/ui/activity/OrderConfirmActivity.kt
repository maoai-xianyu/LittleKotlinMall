package mall.kotlin.com.ordercenter.ui.activity

import com.alibaba.android.arouter.facade.annotation.Route
import mall.kotlin.com.baselibrary.ui.activity.BaseUIActivity
import mall.kotlin.com.ordercenter.R
import mall.kotlin.com.provider.router.RouterPath

/**
 * author:    zhangkun .
 * date:    on 2019/4/2.
 */
@Route(path = RouterPath.OrderCenter.PATH_ORDER_CONFIRM)
class OrderConfirmActivity : BaseUIActivity() {

    override fun setView(): Int {
        return R.layout.activity_order_confirm
    }
}