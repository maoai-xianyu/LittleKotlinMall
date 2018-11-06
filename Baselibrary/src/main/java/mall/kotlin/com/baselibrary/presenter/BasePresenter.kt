package mall.kotlin.com.baselibrary.presenter

import com.trello.rxlifecycle.LifecycleProvider
import mall.kotlin.com.baselibrary.presenter.view.BaseView
import javax.inject.Inject

/**
 * author:  zhangkun .
 * date:    on 2018/10/25.
 */
open class BasePresenter<T : BaseView> {

    lateinit var mView: T

    @Inject
    lateinit var lifecycleProvider: LifecycleProvider<*>
}