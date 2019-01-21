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
import mall.kotlin.com.baselibrary.utils.AppPrefsUtils
import mall.kotlin.com.baselibrary.utils.GlideUtils
import mall.kotlin.com.provider.common.ProviderConstant
import mall.kotlin.com.usercenter.R
import mall.kotlin.com.usercenter.data.protocol.UserInfo
import mall.kotlin.com.usercenter.injection.component.DaggerUserComponent
import mall.kotlin.com.usercenter.injection.module.UserModule
import mall.kotlin.com.usercenter.presenter.UserInfoPresenter
import mall.kotlin.com.usercenter.presenter.view.UserInfoView
import mall.kotlin.com.usercenter.utils.UserPrefsUtils
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
    private var mLocalFileUrl: String? = null
    private var mRemoteFileUrl: String? = null

    private var mUserIcon: String? = null
    private var mUserName: String? = null
    private var mUserGender: String? = null
    private var mUserSign: String? = null
    private var mUserMobile: String? = null

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

        initData()
    }

    private fun initData() {
        mUserIcon = AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_ICON)
        mUserName = AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_NAME)
        mUserGender = AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_GENDER)
        mUserSign = AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_SIGN)
        mUserMobile = AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_MOBILE)

        mRemoteFileUrl = mUserIcon
        if (mUserIcon != "") {
            GlideUtils.loadUrlImage(this, mUserIcon!!, mUserIconIv)
        }

        mUserNameEt.setText(mUserName)
        if (mUserGender == "0") {
            mGenderMaleRb.isChecked = true
        } else {
            mGenderFemaleRb.isChecked = true
        }
        mUserSignEt.setText(mUserSign)

        mUserMobileTv.text = mUserMobile
    }

    override fun setListener() {

        mUserIconIv.onClick {
            showAlertView()
        }

        mHeaderBar.getRightView().onClick {
            mPresenter.editUser(mRemoteFileUrl!!,
                    mUserNameEt.text?.toString() ?: "",
                    if (mGenderMaleRb.isChecked) "0" else "1",
                    mUserSignEt.text?.toString() ?: ""
            )
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
        mLocalFileUrl = result?.image?.compressPath
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

    override fun onGetUploadTokenResult(result: String) {

        mUploadManager.put(mLocalFileUrl, null, result, object : UpCompletionHandler {
            override fun complete(key: String?, info: ResponseInfo?, response: JSONObject?) {
                Logger.d("key $key")
                Logger.d("info $info")
                Logger.json(response.toString())
                mRemoteFileUrl = BaseConstant.IMAGE_SERVER_ADDRESS + response?.get("hash")
                Logger.d("mRemoteFileUrl $mRemoteFileUrl")
                GlideUtils.loadUrlImage(this@UserInfoActivity, mRemoteFileUrl!!, mUserIconIv)
            }
        }, null)

    }

    override fun onEditUserResult(result: UserInfo) {
        toast("修改成功")
        UserPrefsUtils.putUserInfo(result)
    }
}
