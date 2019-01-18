package mall.kotlin.com.baselibrary.ui.activity

import android.os.Bundle
import mall.kotlin.com.baselibrary.BaseApplication
import mall.kotlin.com.baselibrary.injection.component.ActivityComponent
import mall.kotlin.com.baselibrary.injection.component.DaggerActivityComponent
import mall.kotlin.com.baselibrary.injection.module.ActivityModule
import mall.kotlin.com.baselibrary.injection.module.LifecycleProviderModule
import mall.kotlin.com.baselibrary.presenter.BasePresenter
import mall.kotlin.com.baselibrary.presenter.view.BaseView
import mall.kotlin.com.baselibrary.widgets.ProgressLoading
import org.jetbrains.anko.toast
import javax.inject.Inject

/**
 * author:  zhangkun .
 * date:    on 2018/10/25.
 */
abstract class BaseMvpActivity<T : BasePresenter<*>> : BaseActivity(), BaseView {

    @Inject
    lateinit var mPresenter: T

    lateinit var activityComponent: ActivityComponent

    private lateinit var mLoadingDialog: ProgressLoading

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.getArgs(this.intent.extras)
        this.setContentView(this.setView())
        initActivityInjection()
        injectComponent()
        initView()
        setListener()
        mLoadingDialog = ProgressLoading.create(this)
    }

    // 获取参数
    abstract fun getArgs(bundle: Bundle?)

    // 布局文件
    abstract fun setView(): Int

    // 控件
    abstract fun initView()

    // 初始化事件
    abstract fun setListener()

    abstract fun injectComponent()

    private fun initActivityInjection() {
        activityComponent = DaggerActivityComponent.builder()
                .appComponent((application as BaseApplication).appComponent)
                .activityModule(ActivityModule(this))
                .lifecycleProviderModule(LifecycleProviderModule(this))
                .build()
    }

    override fun showLoading() {
        mLoadingDialog.showLoading()
    }

    override fun hideLoading() {
        mLoadingDialog.hideLoading()
    }

    override fun onError(text: String) {
        toast(text)
    }


}