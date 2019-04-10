package mall.kotlin.com.ordercenter.ui.adapter

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import kotlinx.android.synthetic.main.layout_order_item.view.*
import mall.kotlin.com.baselibrary.ext.loadUrl
import mall.kotlin.com.baselibrary.ext.onClick
import mall.kotlin.com.baselibrary.ext.setVisible
import mall.kotlin.com.baselibrary.ui.adapter.BaseRecyclerViewAdapter
import mall.kotlin.com.baselibrary.utils.YuanFenConverter
import mall.kotlin.com.ordercenter.R
import mall.kotlin.com.ordercenter.common.OrderConstant
import mall.kotlin.com.ordercenter.common.OrderStatus
import mall.kotlin.com.ordercenter.data.protocol.Order
import org.jetbrains.anko.dip

/*
    订单列表数据适配
 */
class OrderAdapter(context: Context) : BaseRecyclerViewAdapter<Order, OrderAdapter.ViewHolder>(context) {


    var listener: OnOptClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext)
                .inflate(R.layout.layout_order_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val model = dataList[position]
        var mTotalCount: Int = 0
        if (model.orderGoodsList.size == 1) {
            holder.itemView.mMultiGoodsView.setVisible(false)
            holder.itemView.mSingleGoodsView.setVisible(true)

            val orderGoods = model.orderGoodsList[0]
            holder.itemView.mGoodsIconIv.loadUrl(orderGoods.goodsIcon)
            holder.itemView.mGoodsDescTv.text = orderGoods.goodsDesc
            holder.itemView.mGoodsPriceTv.text = YuanFenConverter.changeF2YWithUnit(orderGoods.goodsPrice)
            holder.itemView.mGoodsCountTv.text = "x${orderGoods.goodsCount}个"
            mTotalCount = orderGoods.goodsCount
        } else {
            holder.itemView.mSingleGoodsView.setVisible(false)
            holder.itemView.mMultiGoodsView.setVisible(true)
            holder.itemView.mMultiGoodsView.removeAllViews()
            for (orderGoods in model.orderGoodsList) {
                val imageView = ImageView(mContext)
                val lp = ViewGroup.MarginLayoutParams(mContext.dip(60.0f),
                        mContext.dip(60.0f))

                lp.rightMargin = mContext.dip(15f)
                imageView.layoutParams = lp
                imageView.loadUrl(orderGoods.goodsIcon)
                holder.itemView.mMultiGoodsView.addView(imageView)
                mTotalCount += orderGoods.goodsCount
            }
        }
        holder.itemView.mOrderInfoTv.text = "合计${mTotalCount}件商品，总价${YuanFenConverter.changeF2YWithUnit(model.totalPrice)}"

        when (model.orderStatus) {
            OrderStatus.ORDER_WAIT_PAY -> {
                holder.itemView.mOrderStatusNameTv.text = "待支付"
                holder.itemView.mOrderStatusNameTv.setTextColor(ContextCompat.getColor(mContext, R.color.common_red))
                setOptVisible(holder, false)
            }
            OrderStatus.ORDER_WAIT_CONFIRM -> {
                holder.itemView.mOrderStatusNameTv.text = "待收货"
                holder.itemView.mOrderStatusNameTv.setTextColor(ContextCompat.getColor(mContext, R.color.common_blue))
                setOptVisible(holder, confirmVisible = true, waitPayVisible = false)
            }

            OrderStatus.ORDER_COMPLETED -> {
                holder.itemView.mOrderStatusNameTv.text = "已完成"
                holder.itemView.mOrderStatusNameTv.setTextColor(ContextCompat.getColor(mContext, R.color.common_yellow))
                setOptVisible(holder, confirmVisible = false, waitPayVisible = false, cancelVisible = false)
            }

            OrderStatus.ORDER_CANCELED -> {
                holder.itemView.mOrderStatusNameTv.text = "已取消"
                holder.itemView.mOrderStatusNameTv.setTextColor(ContextCompat.getColor(mContext, R.color.common_gray))
                setOptVisible(holder, confirmVisible = false, waitPayVisible = false, cancelVisible = false)
            }

        }

        holder.itemView.mPayBtn.onClick {
            listener?.let {
                it.onOptClick(OrderConstant.OPT_ORDER_PAY, model)
            }
        }

        holder.itemView.mConfirmBtn.onClick {
            listener?.let {
                it.onOptClick(OrderConstant.OPT_ORDER_CONFIRM, model)
            }
        }

        holder.itemView.mCancelBtn.onClick {
            listener?.let {
                it.onOptClick(OrderConstant.OPT_ORDER_CANCEL, model)
            }
        }

    }

    private fun setOptVisible(holder: ViewHolder, confirmVisible: Boolean, waitPayVisible: Boolean = true, cancelVisible: Boolean = true) {
        holder.itemView.mConfirmBtn.setVisible(confirmVisible)
        holder.itemView.mPayBtn.setVisible(waitPayVisible)
        holder.itemView.mCancelBtn.setVisible(cancelVisible)
        if (confirmVisible or waitPayVisible or cancelVisible) {
            holder.itemView.mBottomView.setVisible(true)
        } else {
            holder.itemView.mBottomView.setVisible(false)
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

    interface OnOptClickListener {
        fun onOptClick(optType: Int, order: Order)
    }

}
