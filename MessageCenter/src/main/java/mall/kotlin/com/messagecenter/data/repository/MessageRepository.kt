package mall.kotlin.com.messagecenter.data.repository

import mall.kotlin.com.baselibrary.data.net.RetrofitFactory
import mall.kotlin.com.baselibrary.data.protocol.BaseResp
import mall.kotlin.com.messagecenter.data.api.MessageApi
import mall.kotlin.com.messagecenter.data.protocol.Message
import rx.Observable
import javax.inject.Inject

/*
    消息列表层
 */
class MessageRepository @Inject constructor() {

    /*
        消息列表层
     */
    fun getMessageList(): Observable<BaseResp<MutableList<Message>?>> {
        return RetrofitFactory.instance.create(MessageApi::class.java)
                .getMessageList()
    }

}
