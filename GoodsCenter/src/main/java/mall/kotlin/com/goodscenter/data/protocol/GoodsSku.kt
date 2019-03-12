package mall.kotlin.com.goodscenter.data.protocol

/**
 * author:  zhangkun .
 * date:    on 2019/3/11.
 * 商品SKU数据类
 */
data class GoodsSku(
        val id: Int,
        val skuTitle: String,//SKU标题
        val skuContent: List<String>//SKU内容
)