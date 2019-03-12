package mall.kotlin.com.goodscenter.ui.fragment

import mall.kotlin.com.baselibrary.ui.fragment.BaseMvpFragment
import mall.kotlin.com.goodscenter.R
import mall.kotlin.com.goodscenter.data.protocol.Goods
import mall.kotlin.com.goodscenter.presenter.GoodsDetailPresenter
import mall.kotlin.com.goodscenter.presenter.view.GoodsDetailView

/**
 * authorhangkun .
 * date:    on 2019/3/11.
 */
class GoodsDetailTabOneFragment : BaseMvpFragment<GoodsDetailPresenter>(), GoodsDetailView {


    override fun setView(): Int {
        return R.layout.fragment_goods_detail_tab_one
    }

    override fun initView() {

    }

    override fun setListener() {

    }

    override fun start() {

    }

    override fun injectComponent() {
    }

    override fun onGetGoodsDetailResult(result: Goods) {
    }
}