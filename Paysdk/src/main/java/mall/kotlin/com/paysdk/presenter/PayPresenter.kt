package mall.kotlin.com.paysdk.presenter

import mall.kotlin.com.baselibrary.ext.execute
import mall.kotlin.com.baselibrary.presenter.BasePresenter
import mall.kotlin.com.baselibrary.rx.BaseSubscriber
import mall.kotlin.com.paysdk.presenter.view.PayView
import mall.kotlin.com.paysdk.service.PayService
import timber.log.Timber
import javax.inject.Inject

/**
 * author:  zhangkun .
 * date:    on 2018/10/25.
 */
class PayPresenter @Inject constructor() : BasePresenter<PayView>() {

    @Inject
    lateinit var payService: PayService


    fun getPaySign(orderId: Int, totalPrice: Long) {
        /**
         * 业务逻辑
         */
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        payService.getPaySign(orderId,totalPrice)
                .execute(object : BaseSubscriber<String>(mView) {
                    override fun onNext(t: String) {

                        Timber.d("sign : $t")
                        mView.onGetSignResult(t)
                    }
                }, lifecycleProvider)

    }
}