package mall.kotlin.com.baselibrary.ui.fragment

import android.support.v4.app.Fragment
import com.trello.rxlifecycle.components.support.RxFragment

/**
 * author:  zhangkun .
 * date:    on 2018/11/05.
 */
open class BaseFragment : RxFragment() {

    override fun onDetach() {
        super.onDetach()
        //解决java.lang.IllegalStateException: Activity has been destroyed 的错误
        try {
            val childFragmentManager = Fragment::class.java.getDeclaredField("mChildFragmentManager")
            childFragmentManager.isAccessible = true
            childFragmentManager.set(this, null)
        } catch (e: NoSuchFieldException) {
            throw RuntimeException(e)
        } catch (e: IllegalAccessException) {
            throw RuntimeException(e)
        }
    }
}