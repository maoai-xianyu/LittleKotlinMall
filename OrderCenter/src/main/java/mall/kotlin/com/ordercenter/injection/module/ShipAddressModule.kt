package mall.kotlin.com.ordercenter.injection.module

import dagger.Module
import dagger.Provides
import mall.kotlin.com.ordercenter.service.ShipAddressService
import mall.kotlin.com.ordercenter.service.impl.ShipAddressServiceImpl

/**
 * author:  zhangkun .
 * date:    on 2018/11/2.
 */
@Module
class ShipAddressModule {

    @Provides
    fun providesShipAddressService(service: ShipAddressServiceImpl): ShipAddressService {
        return service
    }
}