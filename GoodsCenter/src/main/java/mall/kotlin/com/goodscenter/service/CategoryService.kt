package mall.kotlin.com.goodscenter.service

import mall.kotlin.com.goodscenter.data.protocol.Category
import rx.Observable

/**
 * author:  zhangkun .
 * date:    on 2018/10/25.
 */
interface CategoryService {


    fun getCategory(parentId: Int): Observable<MutableList<Category>?>

}