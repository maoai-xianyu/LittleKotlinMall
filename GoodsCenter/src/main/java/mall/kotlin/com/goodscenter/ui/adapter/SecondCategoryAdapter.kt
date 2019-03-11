package mall.kotlin.com.goodscenter.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.layout_second_category_item.view.*
import mall.kotlin.com.baselibrary.ext.loadUrl
import mall.kotlin.com.baselibrary.ui.adapter.BaseRecyclerViewAdapter
import mall.kotlin.com.goodscenter.R
import mall.kotlin.com.goodscenter.data.protocol.Category

/**
 * author:  zhangkun .
 * date:    on 2019/1/30.
 */
class SecondCategoryAdapter(context: Context) : BaseRecyclerViewAdapter<Category, SecondCategoryAdapter.ViewHolder>(context) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.layout_second_category_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val category = dataList[position]
        //GlideUtils.loadUrlImage(mContext, category.categoryIcon, holder.itemView.mCategoryIconIv)
        holder.itemView.mCategoryIconIv.loadUrl(category.categoryIcon)
        holder.itemView.mSecondCategoryNameTv.text = category.categoryName
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}