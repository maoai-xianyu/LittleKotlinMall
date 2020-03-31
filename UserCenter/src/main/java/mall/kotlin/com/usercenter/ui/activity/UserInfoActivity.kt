package mall.kotlin.com.usercenter.ui.activity

import com.jph.takephoto.model.TResult
import com.orhanobut.logger.Logger
import com.qiniu.android.http.ResponseInfo
import com.qiniu.android.storage.UpCompletionHandler
import com.qiniu.android.storage.UploadManager
import kotlinx.android.synthetic.main.activity_user_info.*
import mall.kotlin.com.baselibrary.common.BaseConstant
import mall.kotlin.com.baselibrary.ext.onClick
import mall.kotlin.com.baselibrary.ui.activity.BaseTakePhotoActivity
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


/**
 * 用户信息
 */

class UserInfoActivity : BaseTakePhotoActivity<UserInfoPresenter>(), UserInfoView {


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


    override fun injectComponent() {
        DaggerUserComponent.builder()
                .activityComponent(activityComponent)
                .userModule(UserModule())
                .build().inject(this)
        mPresenter.mView = this
    }



    override fun takeSuccess(result: TResult?) {
        mLocalFileUrl = result?.image?.compressPath
        mPresenter.getUploadToken()
    }


    override fun onGetUploadTokenResult(result: String) {

        mUploadManager.put(mLocalFileUrl, null, result, object : UpCompletionHandler {
            override fun complete(key: String?, info: ResponseInfo?, response: JSONObject?) {
                Logger.d("key $key")
                Logger.d("info $info")
                Logger.json(response.toString())
                mRemoteFileUrl = BaseConstant.IMAGE_SERVER_ADDRESS + response?.get("hash")
                Logger.d("mRemoteFileUrl 成功后 $mRemoteFileUrl")
                // 修改项目种的头像，用以显示
                mRemoteFileUrl = "https://kotlin-mall-1252364497.cos.ap-chengdu.myqcloud.com/maoai_xianyu.png"
                Logger.d("mRemoteFileUrl 成功后从新赋值，目前7牛有问题 $mRemoteFileUrl")
                GlideUtils.loadUrlImage(this@UserInfoActivity, mRemoteFileUrl!!, mUserIconIv)
            }
        }, null)

    }

    override fun onEditUserResult(result: UserInfo) {
        toast("修改成功")
        UserPrefsUtils.putUserInfo(result)
    }
}
