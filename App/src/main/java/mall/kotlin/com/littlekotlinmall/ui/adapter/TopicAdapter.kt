package mall.kotlin.com.littlekotlinmall.ui.adapter

import android.content.Context
import androidx.viewpager.widget.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.layout_topic_item.view.*
import mall.kotlin.com.baselibrary.utils.GlideUtils
import mall.kotlin.com.littlekotlinmall.R

/**
 * author:  zhangkun .
 * date:    on 2019/2/21.
 */
class TopicAdapter(private val context: Context, private val list: List<String>) : androidx.viewpager.widget.PagerAdapter() {

    override fun destroyItem(container: ViewGroup, position: Int, paramObject: Any) {
        container.removeView(paramObject as View)
    }

    override fun getCount(): Int {
        return list.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val rootView = LayoutInflater.from(context).inflate(R.layout.layout_topic_item, null)
        //rootView.mTopicIv.loadUrl(list[position])
        GlideUtils.loadUrlImage(context, list[position], rootView.mTopicIv)
        container.addView(rootView)
        return rootView
    }

    override fun isViewFromObject(paramView: View, paramObject: Any): Boolean {
        return paramView === paramObject
    }
}