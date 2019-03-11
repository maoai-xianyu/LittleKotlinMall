package mall.kotlin.com.goodscenter.injection.module

import dagger.Module
import dagger.Provides
import mall.kotlin.com.goodscenter.service.CategoryService
import mall.kotlin.com.goodscenter.service.impl.CategoryServiceImpl

/**
 * author:  zhangkun .
 * date:    on 2018/11/2.
 */
@Module
class CategoryModule {

    @Provides
    fun providesCategoryService(service: CategoryServiceImpl): CategoryService {
        return service
    }
}