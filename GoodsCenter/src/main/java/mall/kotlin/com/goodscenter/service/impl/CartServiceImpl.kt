package mall.kotlin.com.goodscenter.service.impl

import mall.kotlin.com.baselibrary.ext.convert
import mall.kotlin.com.baselibrary.ext.convertBoolean
import mall.kotlin.com.goodscenter.data.protocol.CartGoods
import mall.kotlin.com.goodscenter.data.repository.CartRepository
import mall.kotlin.com.goodscenter.service.CartService
import rx.Observable
import javax.inject.Inject

/**
 *    author : zhangkun .
 *    date   : on 2018/10/25
 */
class CartServiceImpl @Inject constructor() : CartService {

    @Inject
    lateinit var repository: CartRepository


    override fun addCart(goodsId: Int, goodsDesc: String, goodsIcon: String,
                         goodsPrice: Long, goodsCount: Int, goodsSku: String): Observable<Int> {

        return repository.addCart(goodsId, goodsDesc, goodsIcon,
                goodsPrice, goodsCount, goodsSku)
                .convert()

    }


    override fun getCartList(): Observable<MutableList<CartGoods>?> {
        return repository.getCartList().convert()
    }

    override fun deleteCartList(list: List<Int>): Observable<Boolean> {
        return repository.deleteCartList(list).convertBoolean()
}

    override fun submitCart(goodsList: List<CartGoods>, totalPrice: Long): Observable<Int> {
        return repository.submitCart(goodsList, totalPrice).convert()
    }
}