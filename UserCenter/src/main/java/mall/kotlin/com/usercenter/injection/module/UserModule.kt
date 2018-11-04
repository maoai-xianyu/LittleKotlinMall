package mall.kotlin.com.usercenter.injection.module

import dagger.Module
import dagger.Provides
import mall.kotlin.com.usercenter.service.UserService
import mall.kotlin.com.usercenter.service.impl.UserServiceImpl
import mall.kotlin.com.usercenter.service.impl.UserServiceImplNamed
import javax.inject.Named

/**
 * author:  zhangkun .
 * date:    on 2018/11/2.
 */
@Module
class UserModule {

    @Provides
    @Named("service")
    fun providesUserService(service: UserServiceImpl): UserService {
        return service
    }

    @Provides
    @Named("serviceNamed")
    fun providesUserServiceNamed(service: UserServiceImplNamed): UserService {
        return service
    }
}