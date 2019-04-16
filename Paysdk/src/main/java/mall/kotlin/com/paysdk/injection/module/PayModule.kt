package mall.kotlin.com.paysdk.injection.module

import dagger.Module
import dagger.Provides
import mall.kotlin.com.paysdk.service.PayService
import mall.kotlin.com.paysdk.service.impl.PayServiceImpl

/**
 * author:  zhangkun .
 * date:    on 2018/11/2.
 */
@Module
class PayModule {

    @Provides
    fun providesOrderService(service: PayServiceImpl): PayService {
        return service
    }
}