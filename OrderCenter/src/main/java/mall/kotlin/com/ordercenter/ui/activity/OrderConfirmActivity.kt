package mall.kotlin.com.ordercenter.ui.activity

import android.annotation.SuppressLint
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import kotlinx.android.synthetic.main.activity_order_confirm.*
import mall.kotlin.com.baselibrary.ui.activity.BaseMvpActivity
import mall.kotlin.com.baselibrary.utils.YuanFenConverter
import mall.kotlin.com.ordercenter.R
import mall.kotlin.com.ordercenter.data.protocol.Order
import mall.kotlin.com.ordercenter.injection.component.DaggerOrderComponent
import mall.kotlin.com.ordercenter.injection.module.OrderModule
import mall.kotlin.com.ordercenter.presenter.OrderConfirmPresenter
import mall.kotlin.com.ordercenter.presenter.view.OrderConfirmView
import mall.kotlin.com.provider.common.ProviderConstant
import mall.kotlin.com.provider.router.RouterPath

/**
 * author:    zhangkun .
 * date:    on 2019/4/2.
 */
@Route(path = RouterPath.OrderCenter.PATH_ORDER_CONFIRM)
class OrderConfirmActivity : BaseMvpActivity<OrderConfirmPresenter>(), OrderConfirmView {

    private var mOrderId: Int = 0

    override fun setView(): Int {
        return R.layout.activity_order_confirm
    }

    override fun getArgs(bundle: Bundle?) {
        bundle?.let {
            mOrderId = bundle.getInt(ProviderConstant.KEY_ORDER_ID, -1)
        }
    }

    override fun initView() {

    }

    override fun start() {
        mPresenter.getOrderById(mOrderId)
    }

    override fun injectComponent() {
        DaggerOrderComponent.builder().activityComponent(activityComponent)
                .orderModule(OrderModule())
                .build()
                .inject(this)
        mPresenter.mView = this

    }

    @SuppressLint("SetTextI18n")
    override fun onGetOrderByIdResult(result: Order) {
        mTotalPriceTv.text = "合计：${YuanFenConverter.changeF2YWithUnit(result.totalPrice)}"
    }
}