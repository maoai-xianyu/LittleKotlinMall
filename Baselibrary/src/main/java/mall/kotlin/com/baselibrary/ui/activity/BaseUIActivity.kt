package mall.kotlin.com.baselibrary.ui.activity

import android.os.Bundle
import mall.kotlin.com.baselibrary.presenter.view.BaseView
import mall.kotlin.com.baselibrary.widgets.ProgressLoading
import org.jetbrains.anko.toast

/**
 * author:  zhangkun .
 * date:    on 2018/10/25.
 */
abstract class BaseUIActivity : BaseActivity(), BaseView {

    private lateinit var mLoadingDialog: ProgressLoading

    override fun onCreate(savedInstanceState: Bundle?) {
        beforeOnCreate()
        super.onCreate(savedInstanceState)
        this.getArgs(this.intent.extras)
        this.setContentView(this.setView())
        initView()
        setListener()
        mLoadingDialog = ProgressLoading.create(this)
    }

    open fun beforeOnCreate() {
    }

    // 获取参数
    open fun getArgs(bundle: Bundle?) {

    }

    // 布局文件
    abstract fun setView(): Int

    // 控件
    open fun initView() {

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

}