package mall.kotlin.com.baselibrary.ext

import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import com.trello.rxlifecycle.LifecycleProvider
import mall.kotlin.com.baselibrary.data.protocol.BaseResp
import mall.kotlin.com.baselibrary.rx.BaseFunc
import mall.kotlin.com.baselibrary.rx.BaseFuncBoolean
import mall.kotlin.com.baselibrary.rx.BaseSubscriber
import mall.kotlin.com.baselibrary.utils.GlideUtils
import mall.kotlin.com.baselibrary.widgets.DefaultTextWatcher
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
fun View.onClick(listener: View.OnClickListener): View {
    this.setOnClickListener(listener)
    return this
}

// 传入函数作为参数传递
fun View.onClick(method: () -> Unit): View {
    this.setOnClickListener { method() }
    return this
}

// 扩展button是否可用
fun Button.enable(et: EditText, method: () -> Boolean) {
    val btn = this
    et.addTextChangedListener(object : DefaultTextWatcher() {
        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            btn.isEnabled = method()
        }
    })
}

/*
    ImageView加载网络图片
 */
fun ImageView.loadUrl(url: String) {
    GlideUtils.loadUrlImage(context, url, this)
}

/*
    多状态视图开始加载
 */
/*fun MultiStateView.startLoading(){
    viewState = MultiStateView.VIEW_STATE_LOADING
    val loadingView = getView(MultiStateView.VIEW_STATE_LOADING)
    val animBackground = loadingView!!.find<View>(R.id.loading_anim_view).background
    (animBackground as AnimationDrawable).start()
}*/

/*
    扩展视图可见性
 */
fun View.setVisible(visible: Boolean) {
    this.visibility = if (visible) View.VISIBLE else View.GONE
}

