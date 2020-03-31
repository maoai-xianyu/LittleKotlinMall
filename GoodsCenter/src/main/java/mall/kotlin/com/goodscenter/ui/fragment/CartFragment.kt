package mall.kotlin.com.goodscenter.ui.fragment

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alibaba.android.arouter.launcher.ARouter
import com.eightbitlab.rxbus.Bus
import com.eightbitlab.rxbus.registerInBus
import com.kennyc.view.MultiStateView
import kotlinx.android.synthetic.main.fragment_cart.*
import mall.kotlin.com.baselibrary.ext.onClick
import mall.kotlin.com.baselibrary.ext.setVisible
import mall.kotlin.com.baselibrary.ext.startLoading
import mall.kotlin.com.baselibrary.ui.fragment.BaseMvpFragment
import mall.kotlin.com.baselibrary.utils.AppPrefsUtils
import mall.kotlin.com.baselibrary.utils.YuanFenConverter
import mall.kotlin.com.goodscenter.R
import mall.kotlin.com.goodscenter.common.GoodsConstant
import mall.kotlin.com.goodscenter.data.protocol.CartGoods
import mall.kotlin.com.goodscenter.event.CartAllCheckedEvent
import mall.kotlin.com.goodscenter.event.UpdateCartSizeEvent
import mall.kotlin.com.goodscenter.event.UpdateTotalPriceEvent
import mall.kotlin.com.goodscenter.injection.component.DaggerCartComponent
import mall.kotlin.com.goodscenter.injection.module.CartModule
import mall.kotlin.com.goodscenter.presenter.CartListPresenter
import mall.kotlin.com.goodscenter.presenter.view.CartListView
import mall.kotlin.com.goodscenter.ui.adapter.CartGoodsAdapter
import mall.kotlin.com.provider.common.ProviderConstant
import mall.kotlin.com.provider.router.RouterPath
import org.jetbrains.anko.support.v4.toast
import timber.log.Timber

/**
 * author zhangkun .
 * date:    on 2019/3/11.
 */
class CartFragment : BaseMvpFragment<CartListPresenter>(), CartListView {

    private val cartAdapter by lazy {
        CartGoodsAdapter(context!!)
    }

    private var mTotalPrice: Long = 0


    override fun setView(): Int {
        return R.layout.fragment_cart
    }

    override fun initView() {
        mCartGoodsRv.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context) as androidx.recyclerview.widget.RecyclerView.LayoutManager?
        mCartGoodsRv.adapter = cartAdapter

        initObserve()

    }

    override fun setListener() {

        mHeaderBar.getRightView().onClick {
            refreshEditStatus()

        }

        mAllCheckedCb.onClick {
            for (item in cartAdapter.dataList) {
                item.isSelected = mAllCheckedCb.isChecked
            }
            cartAdapter.notifyDataSetChanged()
            updateTotalPrice()
        }

        mDeleteBtn.onClick {
            val cartIdList: MutableList<Int> = arrayListOf()
            cartAdapter.dataList.filter {
                it.isSelected
            }.mapTo(cartIdList) { it.id }

            if (cartIdList.size == 0) {
                toast("请选择需要删除的数据")
            } else {
                mPresenter.deleteCartList(cartIdList)
            }
        }

        mSettleAccountsBtn.onClick {
            val cartGoodsList: MutableList<CartGoods> = arrayListOf()
            cartAdapter.dataList.filter {
                it.isSelected
            }.mapTo(cartGoodsList) {
                it
            }

            if (cartGoodsList.size == 0) {
                toast("请选择需要购买的数据")
            } else {
                mPresenter.submitCart(cartGoodsList, mTotalPrice)
            }

        }

    }

    private fun refreshEditStatus() {
        val isEditStatus = getString(R.string.common_edit) == mHeaderBar.getRightText()
        mTotalPriceTv.setVisible(isEditStatus.not())
        mSettleAccountsBtn.setVisible(isEditStatus.not())
        mDeleteBtn.setVisible(isEditStatus)

        mHeaderBar.getRightView().text = if (isEditStatus)
            getString(R.string.common_complete)
        else
            getString(R.string.common_edit)

    }

    override fun start() {
    }

    override fun onStart() {
        super.onStart()
        loadData()

    }

    private fun loadData() {
        mMultiStateView.startLoading()
        mPresenter.getCartList()
    }

    override fun injectComponent() {
        DaggerCartComponent.builder().activityComponent(activityComponent)
                .cartModule(CartModule())
                .build()
                .inject(this)

        mPresenter.mView = this
    }

    override fun onGetCartListResult(result: MutableList<CartGoods>?) {
        if (result != null && result.size > 0) {
            cartAdapter.setData(result)
            mHeaderBar.getRightView().setVisible(true)
            mAllCheckedCb.isChecked = false
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_CONTENT
        } else {
            mHeaderBar.getRightView().setVisible(false)
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_EMPTY
        }
        AppPrefsUtils.putInt(GoodsConstant.SP_CART_SIZE, result?.size ?: 0)
        Bus.send(UpdateCartSizeEvent())
        updateTotalPrice()
    }

    override fun onDeleteCartListResult(result: Boolean) {
        toast("删除成功")
        refreshEditStatus()
        loadData()
    }

    override fun onSubmitCartListResult(result: Int) {
        Timber.d("订单   $result")
        ARouter.getInstance()
                .build(RouterPath.OrderCenter.PATH_ORDER_CONFIRM)
                .withInt(ProviderConstant.KEY_ORDER_ID, result)
                .navigation()

    }

    private fun initObserve() {
        Bus.observe<CartAllCheckedEvent>()
                .subscribe {
                    mAllCheckedCb.isChecked = it.isAllChecked
                    updateTotalPrice()
                }.registerInBus(this)

        Bus.observe<UpdateTotalPriceEvent>()
                .subscribe {
                    updateTotalPrice()
                }.registerInBus(this)
    }


    private fun updateTotalPrice() {
        mTotalPrice = cartAdapter.dataList.filter { it.isSelected }
                .map { it.goodsCount * it.goodsPrice }
                .sum()

        mTotalPriceTv.text = "合计${YuanFenConverter.changeF2YWithUnit(mTotalPrice)}"

    }

    override fun onDestroy() {
        super.onDestroy()
        Bus.unregister(this)
    }

    fun setBackVisible(isVisible: Boolean) {
        mHeaderBar.getLeftView().setVisible(isVisible)
    }

}