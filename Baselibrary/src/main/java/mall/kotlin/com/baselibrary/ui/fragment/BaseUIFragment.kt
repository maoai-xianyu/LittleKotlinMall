package mall.kotlin.com.baselibrary.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import mall.kotlin.com.baselibrary.presenter.view.BaseView
import mall.kotlin.com.baselibrary.widgets.ProgressLoading
import org.jetbrains.anko.support.v4.toast

/**
 * author:  zhangkun .
 * date:    on 2018/11/05.
 */
abstract class BaseUIFragment : BaseFragment(), BaseView {

    lateinit var rootView: View

    private lateinit var mLoadingDialog: ProgressLoading


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        getArgs(arguments)
        initSetting()
        mLoadingDialog = ProgressLoading.create(context!!)
        rootView = inflater.inflate(setView(), null)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        start()
        setListener()
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
    }

    override fun hideLoading() {
    }

    override fun onError(text: String) {
        toast(text)
    }
}