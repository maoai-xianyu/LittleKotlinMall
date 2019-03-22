package mall.kotlin.com.baselibrary.ui.activity

import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import com.trello.rxlifecycle.components.support.RxAppCompatActivity
import mall.kotlin.com.baselibrary.common.AppManager
import org.jetbrains.anko.find

/**
 * author:  zhangkun .
 * date:    on 2018/10/25.
 */
open class BaseActivity : RxAppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppManager.instance.addActivity(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        AppManager.instance.finishActivity(this)
    }

    // decoview
    val contentView: View
        get() {
            val content = find<FrameLayout>(android.R.id.content)
            return content.getChildAt(0)
        }


}