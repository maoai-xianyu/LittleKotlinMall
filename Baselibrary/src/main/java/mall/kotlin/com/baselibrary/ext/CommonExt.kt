package mall.kotlin.com.baselibrary.ext

import com.trello.rxlifecycle.LifecycleProvider
import com.trello.rxlifecycle.kotlin.bindToLifecycle
import mall.kotlin.com.baselibrary.rx.BaseSubscriber
import rx.Observable

/**
 * author:  zhangkun .
 * date:    on 2018/10/25.
 */
fun <T> Observable<T>.execute(subscriber: BaseSubscriber<T>, lifecycleProvider: LifecycleProvider<*>) {
    this.observeOn(rx.android.schedulers.AndroidSchedulers.mainThread())
            .compose(lifecycleProvider.bindToLifecycle())
            .subscribeOn(rx.schedulers.Schedulers.io())
            .subscribe(subscriber)
}