package mall.kotlin.com.littlekotlinmall.ui.adapter

import android.content.Context
import android.graphics.Paint
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.layout_home_discount_item.view.*
import mall.kotlin.com.baselibrary.ui.adapter.BaseRecyclerViewAdapter
import mall.kotlin.com.baselibrary.utils.GlideUtils
import mall.kotlin.com.littlekotlinmall.R

/**
 * author:  zhangkun .
 * date:    on 2019/1/30.
 */
class HomeDiscountAdapter(context: Context) : BaseRecyclerViewAdapter<String, HomeDiscountAdapter.ViewHolder>(context) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.layout_home_discount_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        GlideUtils.loadUrlImage(mContext, dataList[position], holder.itemView.mGoodsIconIv)
        holder.itemView.mDiscountAfterTv.text = "￥123.00"
        holder.itemView.mDiscountBeforeTv.text = "￥1100.00"
    }

    class ViewHolder(view: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(view) {

        init {
            view.mDiscountBeforeTv.paint.flags = Paint.STRIKE_THRU_TEXT_FLAG
            view.mDiscountBeforeTv.paint.isAntiAlias = true
        }

    }
}