package mall.kotlin.com.goodscenter.data.protocol

/**
 * author:  zhangkun .
 * date:    on 2019/3/11.
 */
data class Category(
        val id: Int, // 分类id
        val categoryName: String, // 分类名称
        val categoryIcon: String = "",//分类图标
        val parentId: Int,// 分类 父级ID
        var isSelected: Boolean = false// 是否被选中
)