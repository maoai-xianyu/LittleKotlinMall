package mall.kotlin.com.baselibrary.rx

import rx.Subscriber

/**
 *    author : zhangkun .
 *    date   : on 2018/10/25
 */
open class BaseSubscriber<T> :Subscriber<T>(){
    override fun onNext(t: T) {
    }

    override fun onCompleted() {
    }

    override fun onError(e: Throwable?) {
    }
}