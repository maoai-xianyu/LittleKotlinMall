package mall.kotlin.com.goodscenter.service

import mall.kotlin.com.goodscenter.data.protocol.CartGoods
import rx.Observable

/**
 * author:  zhangkun .
 * date:    on 2018/10/25.
 * 购物车 业务 接口
 */
interface CartService {

    /**
     *添加商品到购物车
     */
    fun addCart(goodsId: Int, goodsDesc: String, goodsIcon: String,
                goodsPrice: Long, goodsCount: Int, goodsSku: String): Observable<Int>

    /**
     *  获取购物车列表
     */
    fun getCartList(): Observable<MutableList<CartGoods>?>


    /**
     *  删除购物车商品
     */
    fun deleteCartList(list: List<Int>): Observable<Boolean>

    /**
     * 提交购物车商品
     */
    fun submitCart(goodsList: List<CartGoods>, totalPrice: Long): Observable<Int>


}