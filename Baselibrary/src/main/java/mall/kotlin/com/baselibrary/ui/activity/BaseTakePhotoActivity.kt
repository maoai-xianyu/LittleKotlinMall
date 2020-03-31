package mall.kotlin.com.baselibrary.ui.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import com.alibaba.android.arouter.launcher.ARouter
import com.bigkoo.alertview.AlertView
import com.bigkoo.alertview.OnItemClickListener
import com.jph.takephoto.app.TakePhoto
import com.jph.takephoto.app.TakePhotoImpl
import com.jph.takephoto.compress.CompressConfig
import com.jph.takephoto.model.InvokeParam
import com.jph.takephoto.model.TContextWrap
import com.jph.takephoto.model.TResult
import com.jph.takephoto.permission.InvokeListener
import com.jph.takephoto.permission.PermissionManager
import com.jph.takephoto.permission.TakePhotoInvocationHandler
import com.kotlin.base.utils.DateUtils
import com.orhanobut.logger.Logger
import mall.kotlin.com.baselibrary.BaseApplication
import mall.kotlin.com.baselibrary.injection.component.ActivityComponent
import mall.kotlin.com.baselibrary.injection.component.DaggerActivityComponent
import mall.kotlin.com.baselibrary.injection.module.ActivityModule
import mall.kotlin.com.baselibrary.injection.module.LifecycleProviderModule
import mall.kotlin.com.baselibrary.presenter.BasePresenter
import mall.kotlin.com.baselibrary.presenter.view.BaseView
import mall.kotlin.com.baselibrary.widgets.ProgressLoading
import org.jetbrains.anko.toast
import java.io.File
import javax.inject.Inject

/**
 * author:  zhangkun .
 * date:    on 2018/10/25.
 */
abstract class BaseTakePhotoActivity<T : BasePresenter<*>> : BaseActivity(), BaseView, TakePhoto.TakeResultListener, InvokeListener {

    @Inject
    lateinit var mPresenter: T

    private var mTakePhoto: TakePhoto? = null
    private lateinit var mTempFile: File
    private lateinit var invokeParam: InvokeParam


    lateinit var activityComponent: ActivityComponent

    private lateinit var mLoadingDialog: ProgressLoading

    override fun onCreate(savedInstanceState: Bundle?) {
        beforeOnCreate()
        super.onCreate(savedInstanceState)
        this.getArgs(this.intent.extras)
        this.setContentView(this.setView())
        initActivityInjection()
        injectComponent()
        // aroute 注册
        getTakePhoto().onCreate(savedInstanceState)
        ARouter.getInstance().inject(this)
        initView()
        mLoadingDialog = ProgressLoading.create(this)
        setListener()
        start()
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

    // 加载数据
    open fun start() {
    }

    // 初始化事件
    open fun setListener() {

    }

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


    override fun takeSuccess(result: TResult?) {
        Logger.d("compressPath ${result?.image?.originalPath}")
        Logger.d("compressPath ${result?.image?.compressPath}")
    }

    override fun takeCancel() {
        Logger.e("takeCancel ")

    }

    override fun takeFail(result: TResult?, msg: String?) {
        Logger.e("error ")

    }


    /**
     * 获取TakePhoto实例
     * @return
     */
    fun getTakePhoto(): TakePhoto {
        if (mTakePhoto == null) {
            mTakePhoto = TakePhotoInvocationHandler.of(this).bind(TakePhotoImpl(this, this)) as TakePhoto
        }
        return mTakePhoto as TakePhoto
    }


    protected fun showAlertView() {
        AlertView("选择图片", null, "取消", null,
                arrayOf("拍照", "相册"),
                this, AlertView.Style.ActionSheet, OnItemClickListener { o, position ->
            getTakePhoto().onEnableCompress(CompressConfig.ofDefaultConfig(), false)
            when (position) {
                0 -> {
                    toast("拍照")
                    createTempFile()
                    getTakePhoto().onPickFromCapture(Uri.fromFile(mTempFile))
                }
                1 -> {
                    toast("相册")
                    getTakePhoto().onPickFromGallery()
                }
            }
        }).show()
    }

    override fun invoke(invokeParam: InvokeParam): PermissionManager.TPermissionType {
        val type = PermissionManager.checkPermission(TContextWrap.of(this), invokeParam.getMethod())
        if (PermissionManager.TPermissionType.WAIT == type) {
            this.invokeParam = invokeParam
        }
        return type
    }


    private fun createTempFile() {
        val tempFileName = "${DateUtils.curTime}.png"
        if (Environment.MEDIA_MOUNTED == Environment.getExternalStorageState() || !Environment.isExternalStorageRemovable()) {
            this.mTempFile = File(Environment.getExternalStorageDirectory(), tempFileName)
            return
        }
        this.mTempFile = File(filesDir, tempFileName)
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        val type = PermissionManager.onRequestPermissionsResult(requestCode, permissions, grantResults)
        PermissionManager.handlePermissionsResult(this, type, invokeParam, this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        getTakePhoto().onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }


}