package mall.kotlin.com.baselibrary.data.protocol

/**
 * author:  zhangkun .
 * date:    on 2018/10/25.
 */
class BaseResp<out T>(val status: Int, val message: String, val data: T)