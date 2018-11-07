package mall.kotlin.com.baselibrary.widgets

import android.app.Dialog
import android.content.Context
import android.graphics.drawable.AnimationDrawable
import android.view.Gravity
import android.widget.ImageView
import mall.kotlin.com.baselibrary.R

/**
 * author:  zhangkun .
 * date:    on 2018/11/7.
 */
class Progressloading(context: Context, theme: Int) : Dialog(context, theme) {
    companion object {
        private lateinit var mDialog: Progressloading
        private var animDrawable: AnimationDrawable? = null

        fun create(context: Context) {
            mDialog = Progressloading(context, R.style.LightProgressDialog)
            mDialog.setContentView(R.layout.progress_dialog)
            mDialog.setCancelable(true) //是否可以取消
            mDialog.setCanceledOnTouchOutside(false) //点击外面是否可以取消
            mDialog.window!!.attributes.gravity = Gravity.CENTER

            val lp = mDialog.window!!.attributes
            lp.dimAmount = 0.2f  // 设置灰暗程度
            mDialog.window!!.attributes = lp

            val loadingView = mDialog.findViewById<ImageView>(R.id.iv_loading)
            animDrawable = loadingView.background as AnimationDrawable


        }
    }

    fun showLoading(){
        super.show()
        animDrawable?.start()
    }

    fun hideLoading(){
        super.dismiss()
        animDrawable?.stop()
    }
}