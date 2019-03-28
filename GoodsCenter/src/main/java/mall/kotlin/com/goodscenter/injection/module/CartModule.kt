package mall.kotlin.com.goodscenter.injection.module

import dagger.Module
import dagger.Provides
import mall.kotlin.com.goodscenter.service.CartService
import mall.kotlin.com.goodscenter.service.impl.CartServiceImpl

/**
 * author:  zhangkun .
 * date:    on 2018/11/2.
 */
@Module
class CartModule {

    @Provides
    fun providesCartService(service: CartServiceImpl): CartService {
        return service
    }
}