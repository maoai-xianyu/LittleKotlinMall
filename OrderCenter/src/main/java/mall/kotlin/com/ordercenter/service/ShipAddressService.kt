package mall.kotlin.com.ordercenter.service

import rx.Observable

/**
 * author:  zhangkun .
 * date:    on 2018/10/25.
 */
interface ShipAddressService {
    /*
         添加收货地址
      */
    fun addShipAddress(shipUserName: String, shipUserMobile: String, shipAddress: String): Observable<Boolean>
}