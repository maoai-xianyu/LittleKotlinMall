package mall.kotlin.com.goodscenter.presenter.view

import mall.kotlin.com.baselibrary.presenter.view.BaseView
import mall.kotlin.com.goodscenter.data.protocol.CartGoods

/**
 * author:  zhangkun .
 * date:    on 2018/10/25.
 */
interface CartListView : BaseView {
    fun onGetCartListResult(result: MutableList<CartGoods>?)
    fun onDeleteCartListResult(result: Boolean)
    fun onSubmitCartListResult(result: Int)
}