package mall.kotlin.com.goodscenter.presenter

import mall.kotlin.com.baselibrary.ext.execute
import mall.kotlin.com.baselibrary.presenter.BasePresenter
import mall.kotlin.com.baselibrary.rx.BaseSubscriber
import mall.kotlin.com.goodscenter.data.protocol.Goods
import mall.kotlin.com.goodscenter.presenter.view.GoodsView
import mall.kotlin.com.goodscenter.service.GoodsService
import javax.inject.Inject

/**
 * author:  zhangkun .
 * date:    on 2018/10/25.
 */
class GoodsPresenter @Inject constructor() : BasePresenter<GoodsView>() {

    @Inject
    lateinit var categoryService: GoodsService

    fun getGoodsList(categoryId: Int, pageNo: Int) {

        /**
         * 业务逻辑
         */
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        categoryService.getGoodsList(categoryId, pageNo)
                .execute(object : BaseSubscriber<MutableList<Goods>?>(mView) {
                    override fun onNext(t: MutableList<Goods>?) {
                        mView.onGetGoodsListResult(t)
                    }
                }, lifecycleProvider)
    }

}