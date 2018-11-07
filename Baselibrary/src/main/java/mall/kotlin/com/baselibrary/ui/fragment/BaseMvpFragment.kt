package mall.kotlin.com.baselibrary.ui.fragment

import android.os.Bundle
import mall.kotlin.com.baselibrary.BaseApplication
import mall.kotlin.com.baselibrary.injection.component.ActivityComponent
import mall.kotlin.com.baselibrary.injection.component.DaggerActivityComponent
import mall.kotlin.com.baselibrary.injection.module.ActivityModule
import mall.kotlin.com.baselibrary.injection.module.LifecycleProviderModule
import mall.kotlin.com.baselibrary.presenter.BasePresenter
import mall.kotlin.com.baselibrary.presenter.view.BaseView
import org.jetbrains.anko.support.v4.toast
import javax.inject.Inject

/**
 * author:  zhangkun .
 * date:    on 2018/10/25.
 */
abstract class BaseMvpFragment<T : BasePresenter<*>> : BaseFragment(), BaseView {

    @Inject
    lateinit var mPresenter: T

    lateinit var activityComponent: ActivityComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initActivityInjection()
        injectComponent()
    }

    abstract fun injectComponent()

    private fun initActivityInjection() {

        activityComponent = DaggerActivityComponent.builder()
                .appComponent((activity?.application as BaseApplication).appComponent)
                .activityModule(ActivityModule(activity!!))
                .lifecycleProviderModule(LifecycleProviderModule(this))
                .build()

    }

    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun onError(text: String) {
        toast(text)
    }
}