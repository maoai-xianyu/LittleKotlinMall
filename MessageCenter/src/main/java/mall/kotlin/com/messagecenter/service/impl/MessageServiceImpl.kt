package mall.kotlin.com.messagecenter.service.impl

import mall.kotlin.com.baselibrary.ext.convert
import mall.kotlin.com.messagecenter.data.protocol.Message
import mall.kotlin.com.messagecenter.data.repository.MessageRepository
import mall.kotlin.com.messagecenter.service.MessageService
import rx.Observable
import javax.inject.Inject

/**
 * author:    zhangkun .
 * date:    on 2019-05-10.
 */

class MessageServiceImpl @Inject constructor() : MessageService {


    @Inject
    lateinit var repository: MessageRepository

    override fun getMessageList(): Observable<MutableList<Message>?> {
        return repository.getMessageList().convert()
    }
}