package mall.kotlin.com.usercenter.service

import rx.Observable

/**
 * author:  zhangkun .
 * date:    on 2018/10/25.
 */
interface UploadService {
    fun getUploadToken(): Observable<String>

}