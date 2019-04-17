package mall.kotlin.com.paysdk.presenter.view

import mall.kotlin.com.baselibrary.presenter.view.BaseView

/**
 * author:  zhangkun .
 * date:    on 2018/10/25.
 */
interface PayView : BaseView {
    fun onGetSignResult(result: String)
    fun onPayOrderResult(result: Boolean)
}