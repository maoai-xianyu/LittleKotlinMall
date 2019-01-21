package mall.kotlin.com.usercenter.data.repository

import mall.kotlin.com.baselibrary.data.net.RetrofitFactory
import mall.kotlin.com.baselibrary.data.protocol.BaseResp
import mall.kotlin.com.usercenter.data.api.UploadApi
import rx.Observable
import javax.inject.Inject

/**
 *    author : zhangkun .
 *    date   : on 2018/10/29
 */
class UploadRepository @Inject constructor() {

    fun getUploadToken(): Observable<BaseResp<String>> {
        return RetrofitFactory.instance.create(UploadApi::class.java)
                .getUploadToken()
    }
}