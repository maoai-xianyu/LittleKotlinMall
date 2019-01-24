package mall.kotlin.com.baselibrary.utils

import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import mall.kotlin.com.baselibrary.R

/*
    Glide工具类
 */
object GlideUtils {


    fun loadImage(context: Context, url: String, imageView: ImageView) {

        val options = RequestOptions()
                .centerCrop()
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)

        Glide.with(context)
                .load(url)
                .apply(options)
                .into(imageView)
    }

    fun loadImageFitCenter(context: Context, url: String, imageView: ImageView) {
        val options = RequestOptions()
                .fitCenter()
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
        Glide.with(context)
                .load(url)
                .apply(options)
                .into(imageView)
    }

    /*
        当fragment或者activity失去焦点或者destroyed的时候，Glide会自动停止加载相关资源，确保资源不会被浪费
     */
    fun loadUrlImage(context: Context, url: String, imageView: ImageView) {
        val options = RequestOptions()
                .centerCrop()
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .placeholder(R.drawable.icon_back)
                .error(R.drawable.icon_back)

        val simpleTarget = object : SimpleTarget<Drawable>() {
            override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
                imageView.setImageDrawable(resource)
            }
        }
        Glide.with(context).load(url).apply(options).into(simpleTarget)
    }
}
