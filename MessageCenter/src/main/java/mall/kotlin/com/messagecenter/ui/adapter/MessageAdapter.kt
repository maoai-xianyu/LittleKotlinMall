package mall.kotlin.com.messagecenter.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.layout_message_item.view.*
import mall.kotlin.com.baselibrary.ext.loadUrl
import mall.kotlin.com.baselibrary.ui.adapter.BaseRecyclerViewAdapter
import mall.kotlin.com.messagecenter.R
import mall.kotlin.com.messagecenter.data.protocol.Message

/**
 * author:    zhangkun .
 * date:    on 2019-05-10.
 */
class MessageAdapter(context: Context) : BaseRecyclerViewAdapter<Message, MessageAdapter.ViewHolder>(context) {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.layout_message_item, p0, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val model = dataList[position]
        //消息图标
        holder.itemView.mMsgIconIv.loadUrl(model.msgIcon)
        //消息标题
        holder.itemView.mMsgTitleTv.text = model.msgTitle
        //消息内容
        holder.itemView.mMsgContentTv.text = model.msgContent
        //消息时间
        holder.itemView.mMsgTimeTv.text = model.msgTime

    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}