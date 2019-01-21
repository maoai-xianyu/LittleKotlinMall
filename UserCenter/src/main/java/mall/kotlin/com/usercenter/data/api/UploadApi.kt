package mall.kotlin.com.usercenter.data.api

import mall.kotlin.com.baselibrary.data.protocol.BaseResp
import retrofit2.http.POST
import rx.Observable

/**
 *    author : zhangkun .
 *    date   : on 2018/10/29
 */
interface UploadApi {

    @POST("common/getUploadToken")
    fun getUploadToken(): Observable<BaseResp<String>>

}