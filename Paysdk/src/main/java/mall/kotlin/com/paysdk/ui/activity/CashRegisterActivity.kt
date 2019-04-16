package mall.kotlin.com.paysdk.ui.activity

import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
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
import org.jetbrains.anko.toast

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

    override fun setView(): Int {
        return R.layout.activity_cash_register
    }

    override fun initView() {

        mTotalPriceTv.text = YuanFenConverter.changeF2YWithUnit(mTotalPrice);

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

    override fun onGetSignResult(result: String) {
        toast("result $result")
    }
}