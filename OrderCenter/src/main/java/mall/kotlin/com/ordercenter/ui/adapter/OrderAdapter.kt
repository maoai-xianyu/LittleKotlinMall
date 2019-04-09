package mall.kotlin.com.ordercenter.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.layout_order_item.view.*
import mall.kotlin.com.baselibrary.ext.loadUrl
import mall.kotlin.com.baselibrary.ui.adapter.BaseRecyclerViewAdapter
import mall.kotlin.com.baselibrary.utils.YuanFenConverter
import mall.kotlin.com.ordercenter.R
import mall.kotlin.com.ordercenter.data.protocol.Order

/*
    订单列表数据适配
 */
class OrderAdapter(context: Context) : BaseRecyclerViewAdapter<Order, OrderAdapter.ViewHolder>(context) {


    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext)
                .inflate(R.layout.layout_order_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val model = dataList[position]
        if (model.orderGoodsList.size == 1) {
            val orderGoods = model.orderGoodsList[0]
            holder.itemView.mGoodsIconIv.loadUrl(orderGoods.goodsIcon)
            holder.itemView.mGoodsDescTv.text = orderGoods.goodsDesc
            holder.itemView.mGoodsPriceTv.text = YuanFenConverter.changeF2YWithUnit(orderGoods.goodsPrice)
            holder.itemView.mGoodsCountTv.text = "x${orderGoods.goodsCount}个"
            holder.itemView.mOrderInfoTv.text = "合计${orderGoods.goodsCount}件商品，总价${YuanFenConverter.changeF2YWithUnit(model.totalPrice)}"
        } else {

        }

    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

}
