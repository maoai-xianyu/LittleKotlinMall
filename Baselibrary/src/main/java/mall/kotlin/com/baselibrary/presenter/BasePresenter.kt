package mall.kotlin.com.baselibrary.presenter

import mall.kotlin.com.baselibrary.presenter.view.BaseView

/**
 * author:  zhangkun .
 * date:    on 2018/10/25.
 */
open class BasePresenter<T : BaseView> {

    lateinit var mView: T
}