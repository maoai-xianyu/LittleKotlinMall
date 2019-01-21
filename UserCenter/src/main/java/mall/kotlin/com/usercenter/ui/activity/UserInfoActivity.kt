package mall.kotlin.com.usercenter.ui.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
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
import com.jph.takephoto.permission.PermissionManager.TPermissionType
import com.jph.takephoto.permission.TakePhotoInvocationHandler
import com.kotlin.base.utils.DateUtils
import com.orhanobut.logger.Logger
import com.qiniu.android.http.ResponseInfo
import com.qiniu.android.storage.UpCompletionHandler
import com.qiniu.android.storage.UploadManager
import kotlinx.android.synthetic.main.activity_user_info.*
import mall.kotlin.com.baselibrary.common.BaseConstant
import mall.kotlin.com.baselibrary.ext.onClick
import mall.kotlin.com.baselibrary.ui.activity.BaseMvpActivity
import mall.kotlin.com.baselibrary.utils.GlideUtils
import mall.kotlin.com.usercenter.R
import mall.kotlin.com.usercenter.injection.component.DaggerUserComponent
import mall.kotlin.com.usercenter.injection.module.UserModule
import mall.kotlin.com.usercenter.presenter.UserInfoPresenter
import mall.kotlin.com.usercenter.presenter.view.UserInfoView
import org.jetbrains.anko.toast
import org.json.JSONObject
import java.io.File


/**
 * 用户信息
 */

class UserInfoActivity : BaseMvpActivity<UserInfoPresenter>(), UserInfoView, TakePhoto.TakeResultListener, InvokeListener {


    private var mTakePhoto: TakePhoto? = null
    private lateinit var mTempFile: File
    private lateinit var invokeParam: InvokeParam
    private val mUploadManager: UploadManager by lazy {
        UploadManager()
    }
    private var mLocalFile: String? = null
    private var mRemoteFile: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        getTakePhoto().onCreate(savedInstanceState)
        super.onCreate(savedInstanceState)
    }

    override fun getArgs(bundle: Bundle?) {

    }

    override fun setView(): Int {
        return R.layout.activity_user_info
    }

    override fun initView() {

    }

    override fun setListener() {

        mUserIconIv.onClick {
            showAlertView()
        }


    }

    private fun showAlertView() {
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

    override fun injectComponent() {
        DaggerUserComponent.builder()
                .activityComponent(activityComponent)
                .userModule(UserModule())
                .build().inject(this)
        mPresenter.mView = this
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

    override fun invoke(invokeParam: InvokeParam): TPermissionType {
        val type = PermissionManager.checkPermission(TContextWrap.of(this), invokeParam.getMethod())
        if (TPermissionType.WAIT == type) {
            this.invokeParam = invokeParam
        }
        return type
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


    override fun takeSuccess(result: TResult?) {
        Logger.d("compressPath ${result?.image?.originalPath}")
        Logger.d("compressPath ${result?.image?.compressPath}")
        mLocalFile = result?.image?.compressPath
        mPresenter.getUploadToken()
    }

    override fun takeCancel() {
        Logger.e("takeCancel ")

    }

    override fun takeFail(result: TResult?, msg: String?) {
        Logger.e("error ")

    }

    fun createTempFile() {
        val tempFileName = "${DateUtils.curTime}.png"
        if (Environment.MEDIA_MOUNTED == Environment.getExternalStorageState() || !Environment.isExternalStorageRemovable()) {
            this.mTempFile = File(Environment.getExternalStorageDirectory(), tempFileName)
            return
        }
        this.mTempFile = File(filesDir, tempFileName)
    }

    override fun onGeyUploadTokenResult(result: String) {

        mUploadManager.put(mLocalFile, null, result, object : UpCompletionHandler {
            override fun complete(key: String?, info: ResponseInfo?, response: JSONObject?) {
                Logger.d("key $key")
                Logger.d("info $info")
                Logger.json(response.toString())
                mRemoteFile = BaseConstant.IMAGE_SERVER_ADDRESS + response?.get("hash")
                Logger.d("mRemoteFile $mRemoteFile")
                GlideUtils.loadUrlImage(this@UserInfoActivity, mRemoteFile!!, mUserIconIv)
            }
        }, null)

    }
}
