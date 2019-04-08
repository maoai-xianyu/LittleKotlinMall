package mall.kotlin.com.ordercenter.ui.activity

import kotlinx.android.synthetic.main.activity_edit_address.*
import mall.kotlin.com.baselibrary.ext.onClick
import mall.kotlin.com.baselibrary.ui.activity.BaseMvpActivity
import mall.kotlin.com.ordercenter.R
import mall.kotlin.com.ordercenter.injection.component.DaggerShipAddressComponent
import mall.kotlin.com.ordercenter.injection.module.ShipAddressModule
import mall.kotlin.com.ordercenter.presenter.EditShipAddressPresenter
import mall.kotlin.com.ordercenter.presenter.view.EditShipAddressView
import org.jetbrains.anko.toast

/**
 * author:    zhangkun .
 * date:    on 2019/4/8.
 */
class ShipAddressEditActivity : BaseMvpActivity<EditShipAddressPresenter>(), EditShipAddressView {

    override fun setView(): Int {
        return R.layout.activity_edit_address
    }

    override fun initView() {

    }

    override fun setListener() {

        mSaveBtn.onClick {
            if (mShipNameEt.text.isNullOrEmpty()) {
                toast("名称不能为空")
                return@onClick
            }

            if (mShipMobileEt.text.isNullOrEmpty()) {
                toast("电话不能为空")

                return@onClick
            }

            if (mShipAddressEt.text.isNullOrEmpty()) {
                toast("地址不能为空")

                return@onClick
            }

            mPresenter.addShipAddress(mShipNameEt.text.toString(), mShipMobileEt.text.toString(),
                    mShipAddressEt.text.toString())
        }

    }

    override fun injectComponent() {
        DaggerShipAddressComponent.builder().activityComponent(activityComponent)
                .shipAddressModule(ShipAddressModule())
                .build()
                .inject(this)
        mPresenter.mView = this

    }

    override fun onAddShipAddressResult(result: Boolean) {
        toast("添加地址成功")
        finish()
    }
}