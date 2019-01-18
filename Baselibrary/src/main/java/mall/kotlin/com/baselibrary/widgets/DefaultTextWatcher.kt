package mall.kotlin.com.baselibrary.widgets

import android.text.Editable
import android.text.TextWatcher

/**
 *    author : zhangkun .
 *    date   : on 2018/11/7
 *    默认TextWatcher，空实现
 */
open class DefaultTextWatcher : TextWatcher {
    override fun afterTextChanged(p0: Editable?) {
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
    }
}