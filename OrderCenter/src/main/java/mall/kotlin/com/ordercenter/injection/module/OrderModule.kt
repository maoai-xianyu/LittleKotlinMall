package mall.kotlin.com.ordercenter.injection.module

import dagger.Module
import dagger.Provides
import mall.kotlin.com.ordercenter.service.OrderService
import mall.kotlin.com.ordercenter.service.impl.OrderServiceImpl

/**
 * author:  zhangkun .
 * date:    on 2018/11/2.
 */
@Module
class OrderModule {

    @Provides
    fun providesOrderService(service: OrderServiceImpl): OrderService {
        return service
    }
}