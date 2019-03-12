package mall.kotlin.com.goodscenter.service

import mall.kotlin.com.goodscenter.data.protocol.Goods
import rx.Observable

/**
 * author:  zhangkun .
 * date:    on 2018/10/25.
 */
interface GoodsService {


    fun getGoodsList(categoryId: Int, pageNo: Int): Observable<MutableList<Goods>?>

}