package mall.kotlin.com.baselibrary.ext

import android.view.View
import com.trello.rxlifecycle.LifecycleProvider
import mall.kotlin.com.baselibrary.data.protocol.BaseResp
import mall.kotlin.com.baselibrary.rx.BaseFunc
import mall.kotlin.com.baselibrary.rx.BaseFuncBoolean
import mall.kotlin.com.baselibrary.rx.BaseSubscriber
import rx.Observable

/**
 * author:  zhangkun .
 * date:    on 2018/10/25.
 */

// 执行事件
fun <T> Observable<T>.execute(subscriber: BaseSubscriber<T>, lifecycleProvider: LifecycleProvider<*>) {
    this.observeOn(rx.android.schedulers.AndroidSchedulers.mainThread())
            .compose(lifecycleProvider.bindToLifecycle())
            .subscribeOn(rx.schedulers.Schedulers.io())
            .subscribe(subscriber)
}


fun <T> Observable<BaseResp<T>>.convert(): Observable<T> {
    return this.flatMap(BaseFunc<T>())
}

fun <T> Observable<BaseResp<T>>.convertBoolean(): Observable<Boolean> {
    return this.flatMap(BaseFuncBoolean())
}

// 点击事件
fun View.onClick(listener: View.OnClickListener) {
    this.setOnClickListener(listener)
    this.setOnClickListener { }
}

// 传入函数作为参数传递
fun View.onClick(method: () -> Unit) {
    this.setOnClickListener { method() }
}
