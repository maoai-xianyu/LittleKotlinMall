package mall.kotlin.com.messagecenter.service

import mall.kotlin.com.messagecenter.data.protocol.Message
import rx.Observable

/**
 * author:    zhangkun .
 * date:    on 2019-05-10.
 */

interface MessageService {


    /*
       消息列表层
    */
    fun getMessageList(): Observable<MutableList<Message>?>

}