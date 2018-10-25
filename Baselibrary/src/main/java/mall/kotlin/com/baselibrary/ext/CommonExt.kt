package mall.kotlin.com.baselibrary.ext

import mall.kotlin.com.baselibrary.rx.BaseSubscriber
import rx.Observable

/**
 * author:  zhangkun .
 * date:    on 2018/10/25.
 */
fun <T> Observable<T>.execute(subscriber: BaseSubscriber<T>){
     this.observeOn(rx.android.schedulers.AndroidSchedulers.mainThread())
            .subscribeOn(rx.schedulers.Schedulers.io())
            .subscribe(subscriber)
}