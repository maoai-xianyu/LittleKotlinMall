package mall.kotlin.com.goodscenter.ui.fragment

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.eightbitlab.rxbus.Bus
import com.eightbitlab.rxbus.registerInBus
import com.kennyc.view.MultiStateView
import kotlinx.android.synthetic.main.fragment_cart.*
import mall.kotlin.com.baselibrary.ext.onClick
import mall.kotlin.com.baselibrary.ext.startLoading
import mall.kotlin.com.baselibrary.ui.fragment.BaseMvpFragment
import mall.kotlin.com.goodscenter.R
import mall.kotlin.com.goodscenter.data.protocol.CartGoods
import mall.kotlin.com.goodscenter.event.CartAllCheckedEvent
import mall.kotlin.com.goodscenter.injection.component.DaggerCartComponent
import mall.kotlin.com.goodscenter.injection.module.CartModule
import mall.kotlin.com.goodscenter.presenter.CartListPresenter
import mall.kotlin.com.goodscenter.presenter.view.CartListView
import mall.kotlin.com.goodscenter.ui.adapter.CartGoodsAdapter

/**
 * author zhangkun .
 * date:    on 2019/3/11.
 */
class CartFragment : BaseMvpFragment<CartListPresenter>(), CartListView {

    private val cartAdapter by lazy {
        CartGoodsAdapter(context!!)
    }


    override fun setView(): Int {
        return R.layout.fragment_cart
    }

    override fun initView() {
        mCartGoodsRv.layoutManager = LinearLayoutManager(context) as RecyclerView.LayoutManager?
        mCartGoodsRv.adapter = cartAdapter

        initObserve()

    }

    override fun setListener() {

        mAllCheckedCb.onClick {
            for (item in cartAdapter.dataList) {
                item.isSelected = mAllCheckedCb.isChecked
            }
            cartAdapter.notifyDataSetChanged()
        }

    }

    override fun start() {
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
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_CONTENT
        } else {
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_EMPTY
        }

    }


    private fun initObserve() {


        Bus.observe<CartAllCheckedEvent>()
                .subscribe {
                    mAllCheckedCb.isChecked = it.isAllChecked
                }.registerInBus(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        Bus.unregister(this)
    }

}