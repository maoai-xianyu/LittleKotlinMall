package mall.kotlin.com.goodscenter.data.protocol

/**
 *    author : zhangkun .
 *    date   : on 2018/10/29
 *    获取分类列表请求，parentId 为0是一级分类
 */
data class CategoryReq(val parentId: Int)