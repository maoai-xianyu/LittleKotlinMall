package mall.kotlin.com.goodscenter.ui.adapter

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.layout_search_history_item.view.*
import mall.kotlin.com.baselibrary.ui.adapter.BaseRecyclerViewAdapter
import mall.kotlin.com.goodscenter.R

/*
    搜索历史记录
 */
class SearchHistoryAdapter(context: Context) : BaseRecyclerViewAdapter<String, SearchHistoryAdapter.ViewHolder>(context) {

   override fun onCreateViewHolder(parent: ViewGroup,
                           viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext)
                .inflate(R.layout.layout_search_history_item,
                        parent,
                        false)
       return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val model = dataList[position]
        holder.itemView.mSearchHistoryTv.text = model

    }

    class ViewHolder(view: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(view)
}
