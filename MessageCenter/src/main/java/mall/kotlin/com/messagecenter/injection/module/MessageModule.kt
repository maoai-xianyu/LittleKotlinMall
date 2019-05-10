package mall.kotlin.com.messagecenter.injection.module

import dagger.Module
import dagger.Provides
import mall.kotlin.com.messagecenter.service.MessageService
import mall.kotlin.com.messagecenter.service.impl.MessageServiceImpl

/**
 * author:    zhangkun .
 * date:    on 2019-05-10.
 */
@Module
class MessageModule {

    @Provides
    fun providesMessageService(service: MessageServiceImpl): MessageService {
        return service
    }
}