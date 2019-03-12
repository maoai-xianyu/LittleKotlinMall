package mall.kotlin.com.goodscenter.injection.module

import dagger.Module
import dagger.Provides
import mall.kotlin.com.goodscenter.service.GoodsService
import mall.kotlin.com.goodscenter.service.impl.GoodsServiceImpl

/**
 * author:  zhangkun .
 * date:    on 2018/11/2.
 */
@Module
class GoodsModule {

    @Provides
    fun providesGoodsService(service: GoodsServiceImpl): GoodsService {
        return service
    }
}