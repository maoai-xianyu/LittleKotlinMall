package mall.kotlin.com.usercenter.injection.module

import dagger.Module
import dagger.Provides
import mall.kotlin.com.usercenter.service.UserService
import mall.kotlin.com.usercenter.service.impl.UserServiceImpl

/**
 * author:  zhangkun .
 * date:    on 2018/11/2.
 */
@Module
class UserModule {

    @Provides
    fun providesUserService(service: UserServiceImpl): UserService {
        return service
    }
}