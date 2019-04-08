package mall.kotlin.com.ordercenter.ui.activity

import android.support.v7.widget.LinearLayoutManager
import com.kennyc.view.MultiStateView
import kotlinx.android.synthetic.main.activity_address.*
import mall.kotlin.com.baselibrary.ext.onClick
import mall.kotlin.com.baselibrary.ext.startLoading
import mall.kotlin.com.baselibrary.ui.activity.BaseMvpActivity
import mall.kotlin.com.ordercenter.R
import mall.kotlin.com.ordercenter.data.protocol.ShipAddress
import mall.kotlin.com.ordercenter.injection.component.DaggerShipAddressComponent
import mall.kotlin.com.ordercenter.injection.module.ShipAddressModule
import mall.kotlin.com.ordercenter.presenter.ShipAddressPresenter
import mall.kotlin.com.ordercenter.presenter.view.ShipAddressView
import mall.kotlin.com.ordercenter.ui.adapter.ShipAddressAdapter
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

/**
 * author:    zhangkun .
 * date:    on 2019/4/8.
 */
class ShipAddressActivity : BaseMvpActivity<ShipAddressPresenter>(), ShipAddressView {

    private lateinit var adapter: ShipAddressAdapter

    override fun setView(): Int {
        return R.layout.activity_address
    }

    override fun initView() {

        adapter = ShipAddressAdapter(this)
        mAddressRv.layoutManager = LinearLayoutManager(this)
        mAddressRv.adapter = adapter

    }


    override fun onStart() {
        super.onStart()
        loadData()
    }

    private fun loadData() {
        mMultiStateView.startLoading()
        mPresenter.getShipAddressList()
    }

    override fun setListener() {

        mAddAddressBtn.onClick {
            startActivity<ShipAddressEditActivity>()

        }

        adapter.mOptClickListener = object : ShipAddressAdapter.OnOptClickListener {
            override fun onSetDefault(address: ShipAddress) {
                toast("设置默认")
                mPresenter.setDefaultShipAddress(address)
            }

            override fun onEdit(address: ShipAddress) {
                toast("编辑")

            }

            override fun onDelete(address: ShipAddress) {
                toast("删除")

            }
        }
    }

    override fun injectComponent() {
        DaggerShipAddressComponent.builder().activityComponent(activityComponent)
                .shipAddressModule(ShipAddressModule())
                .build().inject(this)
        mPresenter.mView = this

    }


    override fun onGetShipAddressResult(result: MutableList<ShipAddress>?) {
        if (result != null && result.size > 0) {
            adapter.setData(result)
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_CONTENT
        } else {
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_EMPTY
        }
    }

    override fun onSetDefaultAddressResult(result: Boolean) {
        toast("设置默认成功")
        loadData()
    }
}