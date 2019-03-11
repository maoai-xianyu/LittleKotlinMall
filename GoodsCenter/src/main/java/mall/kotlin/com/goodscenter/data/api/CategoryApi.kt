package mall.kotlin.com.goodscenter.data.api

import mall.kotlin.com.baselibrary.data.protocol.BaseResp
import mall.kotlin.com.goodscenter.data.protocol.Category
import mall.kotlin.com.goodscenter.data.protocol.CategoryReq
import retrofit2.http.Body
import retrofit2.http.POST
import rx.Observable

/**
 * author:  zhangkun .
 * date:    on 2019/3/11.
 */
interface CategoryApi {

    /**
     * 获取商品分类列表
     */
    @POST("category/getCategory")
    fun getCategory(@Body req: CategoryReq):
            Observable<BaseResp<MutableList<Category>?>>
}