package mall.kotlin.com.usercenter.injection.module

import dagger.Module
import dagger.Provides
import mall.kotlin.com.usercenter.service.UploadService
import mall.kotlin.com.usercenter.service.impl.UploadServiceImpl

/**
 * author:  zhangkun .
 * date:    on 2018/11/2.
 */
@Module
class UploadModule {

    @Provides
    fun providesUploadService(uploadService: UploadServiceImpl): UploadService {
        return uploadService
    }
}