package mall.kotlin.com.goodscenter.presenter.view

import mall.kotlin.com.baselibrary.presenter.view.BaseView
import mall.kotlin.com.goodscenter.data.protocol.Goods

/**
 * author:  zhangkun .
 * date:    on 2018/10/25.
 */
interface GoodsDetailView : BaseView {
    fun onGetGoodsDetailResult(result: Goods)
}