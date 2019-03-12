package mall.kotlin.com.goodscenter.data.api

import mall.kotlin.com.baselibrary.data.protocol.BaseResp
import mall.kotlin.com.goodscenter.data.protocol.GetGoodsDetailReq
import mall.kotlin.com.goodscenter.data.protocol.GetGoodsListByKeywordReq
import mall.kotlin.com.goodscenter.data.protocol.GetGoodsListReq
import mall.kotlin.com.goodscenter.data.protocol.Goods
import retrofit2.http.Body
import retrofit2.http.POST
import rx.Observable

/*
    商品接口
 */
interface GoodsApi {
    /*
        获取商品列表
     */
    @POST("goods/getGoodsList")
    fun getGoodsList(@Body req: GetGoodsListReq): Observable<BaseResp<MutableList<Goods>?>>

    /*
        获取商品列表
     */
    @POST("goods/getGoodsListByKeyword")
    fun getGoodsListByKeyword(@Body req: GetGoodsListByKeywordReq): Observable<BaseResp<MutableList<Goods>?>>

    /*
        获取商品详情
     */
    @POST("goods/getGoodsDetail")
    fun getGoodsDetail(@Body req: GetGoodsDetailReq): Observable<BaseResp<Goods>>
}
