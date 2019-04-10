package mall.kotlin.com.ordercenter.ui.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.eightbitlab.rxbus.Bus
import com.eightbitlab.rxbus.registerInBus
import kotlinx.android.synthetic.main.activity_order_confirm.*
import mall.kotlin.com.baselibrary.ext.onClick
import mall.kotlin.com.baselibrary.ext.setVisible
import mall.kotlin.com.baselibrary.ui.activity.BaseMvpActivity
import mall.kotlin.com.baselibrary.utils.YuanFenConverter
import mall.kotlin.com.ordercenter.R
import mall.kotlin.com.ordercenter.data.protocol.Order
import mall.kotlin.com.ordercenter.event.SelectAddressEvent
import mall.kotlin.com.ordercenter.injection.component.DaggerOrderComponent
import mall.kotlin.com.ordercenter.injection.module.OrderModule
import mall.kotlin.com.ordercenter.presenter.OrderConfirmPresenter
import mall.kotlin.com.ordercenter.presenter.view.OrderConfirmView
import mall.kotlin.com.ordercenter.ui.adapter.OrderGoodsAdapter
import mall.kotlin.com.provider.common.ProviderConstant
import mall.kotlin.com.provider.router.RouterPath
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

/**
 * author:    zhangkun .
 * date:    on 2019/4/2.
 */
@Route(path = RouterPath.OrderCenter.PATH_ORDER_CONFIRM)
class OrderConfirmActivity : BaseMvpActivity<OrderConfirmPresenter>(), OrderConfirmView {


    // route 方式获取id值
    @Autowired(name = ProviderConstant.KEY_ORDER_ID)
    @JvmField
    var mOrderId: Int = 0

    private val mAdapter by lazy {
        OrderGoodsAdapter(this)
    }

    private var mOrder: Order? = null

    override fun setView(): Int {
        return R.layout.activity_order_confirm
    }

    override fun getArgs(bundle: Bundle?) {
        // 传统方式获取数据
        /*bundle?.let {
            mOrderId = bundle.getInt(ProviderConstant.KEY_ORDER_ID, -1)
        }*/
    }

    override fun initView() {
        // 注册
        mOrderGoodsRv.layoutManager = LinearLayoutManager(this) as RecyclerView.LayoutManager?
        mOrderGoodsRv.adapter = mAdapter
        initObserve()
    }

    private fun initObserve() {
        Bus.observe<SelectAddressEvent>()
                .subscribe { t: SelectAddressEvent ->
                    run {
                        mOrder?.let {
                            it.shipAddress = t.address
                        }
                        updateAddressView()
                    }
                }.registerInBus(this)
    }

    @SuppressLint("SetTextI18n")
    private fun updateAddressView() {
        mOrder?.let {
            if (it.shipAddress == null) {
                mSelectShipTv.setVisible(true)
                mShipView.setVisible(false)
            } else {
                mSelectShipTv.setVisible(false)
                mShipView.setVisible(true)
                mShipNameTv.text = it.shipAddress!!.shipUserName + "   " + it.shipAddress!!.shipUserMobile
                mShipAddressTv.text = it.shipAddress!!.shipAddress
            }

        }

    }

    override fun setListener() {
        mSelectShipTv.onClick {
            startActivity<ShipAddressActivity>()
        }

        mShipView.onClick {
            startActivity<ShipAddressActivity>()
        }

        mSubmitOrderBtn.onClick {
            mOrder?.let {
                mPresenter.submitOrder(mOrder!!)
            }
        }
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
        mOrder = result
        mAdapter.setData(result.orderGoodsList)
        mTotalPriceTv.text = "合计：${YuanFenConverter.changeF2YWithUnit(result.totalPrice)}"
        updateAddressView()
    }

    override fun onSubmitOrderResult(result: Boolean) {
        toast("订单提交成功")

    }

    override fun onDestroy() {
        super.onDestroy()
        Bus.unregister(this)
    }
}