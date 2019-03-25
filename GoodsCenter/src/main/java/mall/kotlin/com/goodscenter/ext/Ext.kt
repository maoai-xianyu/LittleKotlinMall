package mall.kotlin.com.goodscenter.ext

import android.widget.EditText
import org.jetbrains.anko.find
import ren.qinc.numberbutton.NumberButton
import ren.qinc.numberbutton.R

/**
 * author:  zhangkun .
 * date:    on 2019/3/25.
 */

/**
 * 扩展访问私有成员
 */
fun NumberButton.getEditText():EditText{
    return find(R.id.text_count)
}