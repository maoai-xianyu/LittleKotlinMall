package mall.kotlin.com.paysdk.service.impl

import mall.kotlin.com.baselibrary.ext.convert
import mall.kotlin.com.paysdk.data.repository.PayRepository
import mall.kotlin.com.paysdk.service.PayService
import rx.Observable
import javax.inject.Inject

/**
 *    author : zhangkun .
 *    date   : on 2018/10/25
 */
class PayServiceImpl @Inject constructor() : PayService {

    @Inject
    lateinit var repository: PayRepository

    override fun getPaySign(orderId: Int, totalPrice: Long): Observable<String> {
        return repository.getPaySign(orderId, totalPrice).convert()
    }
}
