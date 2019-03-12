package mall.kotlin.com.goodscenter.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.layout_goods_item.view.*
import mall.kotlin.com.baselibrary.ext.loadUrl
import mall.kotlin.com.baselibrary.ui.adapter.BaseRecyclerViewAdapter
import mall.kotlin.com.baselibrary.utils.YuanFenConverter
import mall.kotlin.com.goodscenter.R
import mall.kotlin.com.goodscenter.data.protocol.Goods

/**
 * author:  zhangkun .
 * date:    on 2019/1/30.
 */
class GoodsAdapter(context: Context) : BaseRecyclerViewAdapter<Goods, GoodsAdapter.ViewHolder>(context) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.layout_goods_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val goods = dataList[position]
        holder.itemView.mGoodsIconIv.loadUrl(goods.goodsDefaultIcon)
        holder.itemView.mGoodsDescTv.text = goods.goodsDesc
        holder.itemView.mGoodsPriceTv.text = YuanFenConverter.changeF2YWithUnit(goods.goodsDefaultPrice)
        holder.itemView.mGoodsSalesStockTv.text = "销量${goods.goodsSalesCount}件    库存${goods.goodsStockCount}"
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}