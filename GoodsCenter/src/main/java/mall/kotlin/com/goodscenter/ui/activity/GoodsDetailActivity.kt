package mall.kotlin.com.goodscenter.ui.activity

import android.support.design.widget.TabLayout
import com.eightbitlab.rxbus.Bus
import kotlinx.android.synthetic.main.activity_goods_detail.*
import mall.kotlin.com.baselibrary.ext.onClick
import mall.kotlin.com.baselibrary.ui.activity.BaseUIActivity
import mall.kotlin.com.goodscenter.R
import mall.kotlin.com.goodscenter.event.AddCartEvent
import mall.kotlin.com.goodscenter.ui.adapter.GoodsDetailVpAdapter
import mall.kotlin.com.provider.common.afterLogin

/**
 * author:  zhangkun .
 * date:    on 2019/3/12.
 */
class GoodsDetailActivity : BaseUIActivity() {


    override fun setView(): Int {
        return R.layout.activity_goods_detail
    }

    override fun initView() {
        mGoodsDetailTab.tabMode = TabLayout.MODE_FIXED
        mGoodsDetailVp.adapter = GoodsDetailVpAdapter(supportFragmentManager, this)
        mGoodsDetailTab.setupWithViewPager(mGoodsDetailVp)

    }

    override fun start() {
    }

    override fun setListener() {

        mAddCartBtn.onClick {
            afterLogin {
                Bus.send(AddCartEvent())
            }
        }
    }


}