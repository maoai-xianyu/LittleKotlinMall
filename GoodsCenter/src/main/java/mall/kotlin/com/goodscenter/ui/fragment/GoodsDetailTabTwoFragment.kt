package mall.kotlin.com.goodscenter.ui.fragment

import com.eightbitlab.rxbus.Bus
import com.eightbitlab.rxbus.registerInBus
import kotlinx.android.synthetic.main.fragment_goods_detail_tab_two.*
import mall.kotlin.com.baselibrary.ext.loadUrl
import mall.kotlin.com.baselibrary.ui.fragment.BaseUIFragment
import mall.kotlin.com.goodscenter.R
import mall.kotlin.com.goodscenter.event.GoodsDetailImageEvent

/**
 * authorhangkun .
 * date:    on 2019/3/11.
 */
class GoodsDetailTabTwoFragment : BaseUIFragment() {


    override fun setView(): Int {
        return R.layout.fragment_goods_detail_tab_two
    }

    override fun initView() {

        /*  Bus.observe<GoodsDetailImageEvent>()
                  .subscribe {
                      mGoodsDetailOneIv.loadUrl(it.imageOne)
                      mGoodsDetailTwoIv.loadUrl(it.imageTwo)
                  }
                  .registerInBus(this)*/

        Bus.observe<GoodsDetailImageEvent>()
                .subscribe { t: GoodsDetailImageEvent ->
                    run {
                        mGoodsDetailOneIv.loadUrl(t.imageOne)
                        mGoodsDetailTwoIv.loadUrl(t.imageTwo)
                    }
                }
                .registerInBus(this)


    }

    override fun setListener() {


    }

    override fun onDestroy() {
        super.onDestroy()
        Bus.unregister(this)
    }

}