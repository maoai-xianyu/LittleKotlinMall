package mall.kotlin.com.goodscenter.presenter

import mall.kotlin.com.baselibrary.ext.execute
import mall.kotlin.com.baselibrary.presenter.BasePresenter
import mall.kotlin.com.baselibrary.rx.BaseSubscriber
import mall.kotlin.com.goodscenter.data.protocol.CartGoods
import mall.kotlin.com.goodscenter.presenter.view.CartListView
import mall.kotlin.com.goodscenter.service.CartService
import javax.inject.Inject

/**
 * author:  zhangkun .
 * date:    on 2018/10/25.
 */
class CartListPresenter @Inject constructor() : BasePresenter<CartListView>() {

    @Inject
    lateinit var cartService: CartService

    fun getCartList() {

        /**
         * 业务逻辑
         */
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        cartService.getCartList()
                .execute(object : BaseSubscriber<MutableList<CartGoods>?>(mView) {
                    override fun onNext(t: MutableList<CartGoods>?) {
                        mView.onGetCartListResult(t)
                    }
                }, lifecycleProvider)
    }

    fun deleteCartList(list: List<Int>) {

        /**
         * 业务逻辑
         */
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        cartService.deleteCartList(list)
                .execute(object : BaseSubscriber<Boolean>(mView) {
                    override fun onNext(t: Boolean) {
                        mView.onDeleteCartListResult(t)
                    }
                }, lifecycleProvider)
    }


    fun submitCart(goodsList: List<CartGoods>, totalPrice: Long) {

        /**
         * 业务逻辑
         */
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        cartService.submitCart(goodsList, totalPrice)
                .execute(object : BaseSubscriber<Int>(mView) {
                    override fun onNext(t: Int) {
                        mView.onSubmitCartListResult(t)
                    }
                }, lifecycleProvider)

    }

}