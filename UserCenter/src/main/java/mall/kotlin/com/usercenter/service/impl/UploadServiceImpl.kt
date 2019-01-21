package mall.kotlin.com.usercenter.service.impl

import mall.kotlin.com.baselibrary.ext.convert
import mall.kotlin.com.usercenter.data.repository.UploadRepository
import mall.kotlin.com.usercenter.service.UploadService
import rx.Observable
import javax.inject.Inject

/**
 *    author : zhangkun .
 *    date   : on 2018/10/25
 */
class UploadServiceImpl @Inject constructor() : UploadService {

    @Inject
    lateinit var repository: UploadRepository


    override fun getUploadToken(): Observable<String> {
        return repository.getUploadToken()
                .convert()
    }
}