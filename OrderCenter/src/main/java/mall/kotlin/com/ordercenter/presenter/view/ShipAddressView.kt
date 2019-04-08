package mall.kotlin.com.ordercenter.presenter.view

import mall.kotlin.com.baselibrary.presenter.view.BaseView
import mall.kotlin.com.ordercenter.data.protocol.ShipAddress

/**
 * author:  zhangkun .
 * date:    on 2018/10/25.
 */
interface ShipAddressView : BaseView {
    fun onGetShipAddressResult(result: MutableList<ShipAddress>?)
    fun onSetDefaultAddressResult(result: Boolean)
}