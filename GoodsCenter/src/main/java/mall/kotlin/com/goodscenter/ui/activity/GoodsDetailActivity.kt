package mall.kotlin.com.goodscenter.ui.activity

import com.google.android.material.tabs.TabLayout
import android.view.Gravity
import com.eightbitlab.rxbus.Bus
import com.eightbitlab.rxbus.registerInBus
import kotlinx.android.synthetic.main.activity_goods_detail.*
import mall.kotlin.com.baselibrary.ext.onClick
import mall.kotlin.com.baselibrary.ui.activity.BaseUIActivity
import mall.kotlin.com.baselibrary.utils.AppPrefsUtils
import mall.kotlin.com.goodscenter.R
import mall.kotlin.com.goodscenter.common.GoodsConstant
import mall.kotlin.com.goodscenter.event.AddCartEvent
import mall.kotlin.com.goodscenter.event.UpdateCartSizeEvent
import mall.kotlin.com.goodscenter.ui.adapter.GoodsDetailVpAdapter
import mall.kotlin.com.provider.common.afterLogin
import org.jetbrains.anko.startActivity
import q.rorbin.badgeview.QBadgeView

/**
 * author:  zhangkun .
 * date:    on 2019/3/12.
 */
class GoodsDetailActivity : BaseUIActivity() {

    private lateinit var mQBadgeView: QBadgeView


    override fun setView(): Int {
        return R.layout.activity_goods_detail
    }

    override fun initView() {
        mGoodsDetailTab.tabMode = TabLayout.MODE_FIXED
        mGoodsDetailVp.adapter = GoodsDetailVpAdapter(supportFragmentManager, this)
        mGoodsDetailTab.setupWithViewPager(mGoodsDetailVp)

        mQBadgeView = QBadgeView(this)
        initObserve()
        loadCartSize()
    }

    private fun loadCartSize() {
        setCartBadge()
    }

    override fun start() {
    }

    override fun setListener() {


        mLeftIv.onClick {
            finish()
        }

        mAddCartBtn.onClick {
            afterLogin {
                Bus.send(AddCartEvent())
            }
        }

        mEnterCartTv.onClick {
            startActivity<CartActivity>()
        }

    }


    private fun initObserve() {

        Bus.observe<UpdateCartSizeEvent>()
                .subscribe {
                    setCartBadge()
                }.registerInBus(this)
    }

    private fun setCartBadge() {
        mQBadgeView.badgeGravity = Gravity.END or Gravity.TOP
        mQBadgeView.setGravityOffset(22f, -2f, true)
        mQBadgeView.setBadgeTextSize(6f, true)
        mQBadgeView.bindTarget(mEnterCartTv).badgeNumber = AppPrefsUtils.getInt(GoodsConstant.SP_CART_SIZE)
    }

    override fun onDestroy() {
        super.onDestroy()
        Bus.unregister(this)
    }

}