package mall.kotlin.com.goodscenter.ui.adapter

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.layout_top_category_item.view.*
import mall.kotlin.com.baselibrary.ui.adapter.BaseRecyclerViewAdapter
import mall.kotlin.com.goodscenter.R
import mall.kotlin.com.goodscenter.data.protocol.Category

/**
 * author:  zhangkun .
 * date:    on 2019/1/30.
 */
class TopCategoryAdapter(context: Context) : BaseRecyclerViewAdapter<Category, TopCategoryAdapter.ViewHolder>(context) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.layout_top_category_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val category = dataList[position]
        holder.itemView.mTopCategoryNameTv.text = category.categoryName
        holder.itemView.mTopCategoryNameTv.isSelected = category.isSelected
    }

    class ViewHolder(view: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(view)
}