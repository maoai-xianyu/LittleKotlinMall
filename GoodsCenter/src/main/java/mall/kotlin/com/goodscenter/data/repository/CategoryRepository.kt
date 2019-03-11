package mall.kotlin.com.goodscenter.data.repository

import mall.kotlin.com.baselibrary.data.net.RetrofitFactory
import mall.kotlin.com.baselibrary.data.protocol.BaseResp
import mall.kotlin.com.goodscenter.data.api.CategoryApi
import mall.kotlin.com.goodscenter.data.protocol.Category
import mall.kotlin.com.goodscenter.data.protocol.CategoryReq
import rx.Observable
import javax.inject.Inject

/**
 *    author : zhangkun .
 *    date   : on 2018/10/29
 */
class CategoryRepository @Inject constructor() {

    fun getCategory(parentId: Int): Observable<BaseResp<MutableList<Category>?>> {
        return RetrofitFactory.instance.create(CategoryApi::class.java)
                .getCategory(CategoryReq(parentId))
    }


}