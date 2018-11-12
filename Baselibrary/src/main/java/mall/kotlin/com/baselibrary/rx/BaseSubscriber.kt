package mall.kotlin.com.baselibrary.rx

import mall.kotlin.com.baselibrary.presenter.view.BaseView
import rx.Subscriber

/**
 *    author : zhangkun .
 *    date   : on 2018/10/25
 */
open class BaseSubscriber<T>(val baseView: BaseView) : Subscriber<T>() {
    override fun onNext(t: T) {
    }

    override fun onCompleted() {
        baseView.hideLoading()
    }

    override fun onError(e: Throwable?) {
        baseView.hideLoading()
        if (e is BaseException){
            baseView.onError(e.msg)
        }
    }
}