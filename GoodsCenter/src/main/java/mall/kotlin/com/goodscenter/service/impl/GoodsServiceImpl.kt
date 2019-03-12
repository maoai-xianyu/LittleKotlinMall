package mall.kotlin.com.goodscenter.service.impl

import mall.kotlin.com.baselibrary.ext.convert
import mall.kotlin.com.goodscenter.data.protocol.Goods
import mall.kotlin.com.goodscenter.data.repository.GoodsRepository
import mall.kotlin.com.goodscenter.service.GoodsService
import rx.Observable
import javax.inject.Inject

/**
 *    author : zhangkun .
 *    date   : on 2018/10/25
 */
class GoodsServiceImpl @Inject constructor() : GoodsService {

    @Inject
    lateinit var repository: GoodsRepository

    override fun getGoodsList(categoryId: Int, pageNo: Int): Observable<MutableList<Goods>?> {
        return repository.getGoodsList(categoryId, pageNo)
                .convert()
    }

}