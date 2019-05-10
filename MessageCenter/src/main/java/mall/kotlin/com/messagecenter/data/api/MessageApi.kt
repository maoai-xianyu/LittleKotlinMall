package mall.kotlin.com.messagecenter.data.api

import mall.kotlin.com.baselibrary.data.protocol.BaseResp
import mall.kotlin.com.messagecenter.data.protocol.Message
import retrofit2.http.POST
import rx.Observable

/*
    消息接口
 */
interface MessageApi {
    /*
        获取消息列表
     */
    @POST("msg/getList")
    fun getMessageList(): Observable<BaseResp<MutableList<Message>?>>

}
