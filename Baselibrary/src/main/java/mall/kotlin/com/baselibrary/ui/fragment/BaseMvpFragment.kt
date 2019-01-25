package mall.kotlin.com.baselibrary.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import mall.kotlin.com.baselibrary.BaseApplication
import mall.kotlin.com.baselibrary.injection.component.ActivityComponent
import mall.kotlin.com.baselibrary.injection.component.DaggerActivityComponent
import mall.kotlin.com.baselibrary.injection.module.ActivityModule
import mall.kotlin.com.baselibrary.injection.module.LifecycleProviderModule
import mall.kotlin.com.baselibrary.presenter.BasePresenter
import mall.kotlin.com.baselibrary.presenter.view.BaseView
import mall.kotlin.com.baselibrary.widgets.ProgressLoading
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

    private var rootView: View? = null//根布局

    private lateinit var mLoadingDialog: ProgressLoading

    // 懒加载
    private var isVisibleToUser: Boolean = false
    private var isLazyLoad = false//是否已经懒加载
    protected open var useLazyLoad = false//是否使用懒加载

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        getArgs(arguments)
        initSetting()
        initActivityInjection()
        injectComponent()
        //初始加载框
        mLoadingDialog = ProgressLoading.create(context!!)
        rootView = inflater.inflate(setView(), null)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        setListener()
        if (useLazyLoad) {
            if (isVisibleToUser && !isLazyLoad && getView() != null) {
                isLazyLoad = true
                start()
            }
        } else {
            start()
        }
    }

    abstract fun injectComponent()

    private fun initActivityInjection() {

        activityComponent = DaggerActivityComponent.builder()
                .appComponent((activity?.application as BaseApplication).appComponent)
                .activityModule(ActivityModule(activity!!))
                .lifecycleProviderModule(LifecycleProviderModule(this))
                .build()

    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        if (useLazyLoad) {
            this.isVisibleToUser = isVisibleToUser
            if (isVisibleToUser && view != null) {
                if (!isLazyLoad) {
                    isLazyLoad = true
                    start()
                } else {
                    // 从不可见到可见
                    whenViewVisible()
                }
            }
        }

        super.setUserVisibleHint(isVisibleToUser)
    }

    open fun whenViewVisible(){

    }

    // 获取参数
    open fun getArgs(bundle: Bundle?) {

    }

    open fun initSetting() {
    }

    // 布局文件
    abstract fun setView(): Int

    // 控件
    open fun initView() {

    }

    // 加载数据
    open fun start() {
    }

    // 初始化事件
    open fun setListener() {

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


    override fun getView(): View? {
        return rootView
    }

    protected fun isLazyLoad(): Boolean {
        return isLazyLoad
    }

    fun isVisibleToUser(): Boolean {
        return isVisibleToUser
    }


    override fun onDetach() {
        super.onDetach()
        //解决java.lang.IllegalStateException: Activity has been destroyed 的错误
        try {
            val childFragmentManager = Fragment::class.java.getDeclaredField("mChildFragmentManager")
            childFragmentManager.isAccessible = true
            childFragmentManager.set(this, null)
        } catch (e: NoSuchFieldException) {
            throw RuntimeException(e)
        } catch (e: IllegalAccessException) {
            throw RuntimeException(e)
        }
    }
}