package mall.kotlin.com.goodscenter.presenter

import mall.kotlin.com.baselibrary.ext.execute
import mall.kotlin.com.baselibrary.presenter.BasePresenter
import mall.kotlin.com.baselibrary.rx.BaseSubscriber
import mall.kotlin.com.goodscenter.data.protocol.Goods
import mall.kotlin.com.goodscenter.presenter.view.GoodsDetailView
import mall.kotlin.com.goodscenter.service.GoodsService
import javax.inject.Inject

/**
 * author:  zhangkun .
 * date:    on 2018/10/25.
 */
class GoodsDetailPresenter @Inject constructor() : BasePresenter<GoodsDetailView>() {

    @Inject
    lateinit var goodsService: GoodsService

    fun getGoodsDetail(goodsId: Int) {
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        goodsService.getGoodsDetail(goodsId)
                .execute(object : BaseSubscriber<Goods>(mView) {
                    override fun onNext(t: Goods) {
                        mView.onGetGoodsDetailResult(t)
                    }
                }, lifecycleProvider)
    }

}