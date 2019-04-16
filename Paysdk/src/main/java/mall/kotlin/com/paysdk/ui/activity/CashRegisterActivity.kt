package mall.kotlin.com.paysdk.ui.activity

import android.Manifest
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alipay.sdk.app.EnvUtils
import com.alipay.sdk.app.PayTask
import kotlinx.android.synthetic.main.activity_cash_register.*
import mall.kotlin.com.baselibrary.ext.onClick
import mall.kotlin.com.baselibrary.ui.activity.BaseMvpActivity
import mall.kotlin.com.baselibrary.utils.YuanFenConverter
import mall.kotlin.com.paysdk.R
import mall.kotlin.com.paysdk.injection.component.DaggerPayComponent
import mall.kotlin.com.paysdk.injection.module.PayModule
import mall.kotlin.com.paysdk.presenter.PayPresenter
import mall.kotlin.com.paysdk.presenter.view.PayView
import mall.kotlin.com.provider.common.ProviderConstant
import mall.kotlin.com.provider.router.RouterPath
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread
import timber.log.Timber

/**
 * author:    zhangkun .
 * date:    on 2019/4/16.
 */
@Route(path = RouterPath.PaySDK.PATH_PAY)
class CashRegisterActivity : BaseMvpActivity<PayPresenter>(), PayView {


    @Autowired(name = ProviderConstant.KEY_ORDER_ID)
    @JvmField
    var mOrderId: Int = 0

    @Autowired(name = ProviderConstant.KEY_ORDER_PRICE)
    @JvmField
    var mTotalPrice: Long = 0

    /**
     * 获取权限使用的 RequestCode
     */
    private val PERMISSIONS_REQUEST_CODE = 1002

    override fun setView(): Int {
        return R.layout.activity_cash_register
    }

    override fun initView() {
        // 支付宝沙箱联调
        EnvUtils.setEnv(EnvUtils.EnvEnum.SANDBOX)

        mTotalPriceTv.text = YuanFenConverter.changeF2YWithUnit(mTotalPrice)
        requestPermission()
    }

    override fun setListener() {

        mPayBtn.onClick {
            mPresenter.getPaySign(mOrderId, mTotalPrice)
        }
    }

    override fun injectComponent() {
        DaggerPayComponent.builder().activityComponent(activityComponent)
                .payModule(PayModule())
                .build()
                .inject(this)

        mPresenter.mView = this

    }

    /**
     * {resultStatus=9000, result={"alipay_trade_app_pay_response":{"code":"10000","msg":"Success","app_id":"2016072400104913","auth_app_id":"2016072400104913","charset":"utf-8","timestamp":"2019-04-16 20:15:15","out_trade_no":"1259","total_amount":"2.00","trade_no":"2019041622001495901000038757","seller_id":"2088102168712921"},"sign":"IzC9NNdWI0CedYe5T0bLsMiv5UVmAvAtpOIeMto+304rOAwK1e2uWEe84BMBSWh6b7Id67sr5rQZ1/fXbiqvG9K+eVQP/wuzx2Xa/gvk+OkLVheHTR8Q8ST3qhceznFehmqy2X7oBf8y+0H/CSH+OHCc8tdWJpS2tl5JwKb84SS+BXrVcA/zckjULjvsCJxWHtTXacUNwF0XdpnT9dH0w+XRQN3dQrRh6Xfj941zywEO0Yw6JQy7fTDOGCAn7/W0+wM3E/cFUe24xKyfvhC3bZB8xisn9Rs/TRAPkEl7NOnvAdgJqEvzDSMIn865L7C8Td3QANNywOyFe0VvGUDY7g==","sign_type":"RSA2"}, memo=处理成功}
     */
    override fun onGetSignResult(result: String) {
        // 异步线程调用
        doAsync {
            val resultMap: Map<String, String> = PayTask(this@CashRegisterActivity).payV2(result, true)
            Timber.d("resultMap $resultMap")
            //沙箱联调环境账号  ggvpip3079@sandbox.com    密码 111111 支付密码  111111
            uiThread {
                if (resultMap["resultStatus"].equals("9000")) {
                    toast("支付成功")
                } else {
                    toast("支付失败${resultMap["memo"]}")
                }
            }

        }
    }

    /**
     * 检查支付宝 SDK 所需的权限，并在必要的时候动态获取。
     * 在 targetSDK = 23 以上，READ_PHONE_STATE 和 WRITE_EXTERNAL_STORAGE 权限需要应用在运行时获取。
     * 如果接入支付宝 SDK 的应用 targetSdk 在 23 以下，可以省略这个步骤。
     */
    private fun requestPermission() {
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    arrayOf(Manifest.permission.READ_PHONE_STATE, Manifest.permission.WRITE_EXTERNAL_STORAGE), PERMISSIONS_REQUEST_CODE)

        } else {
            toast(getString(R.string.permission_already_granted))
        }
    }

    /**
     * 权限获取回调
     */
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            PERMISSIONS_REQUEST_CODE -> {

                // 用户取消了权限弹窗
                if (grantResults.size == 0) {
                    toast(getString(R.string.permission_rejected))
                    return
                }

                // 用户拒绝了某些权限
                for (x in grantResults) {
                    if (x == PackageManager.PERMISSION_DENIED) {
                        toast(getString(R.string.permission_rejected))
                        return
                    }
                }

                // 所需的权限均正常获取
                toast(getString(R.string.permission_granted))
            }
        }
    }

}