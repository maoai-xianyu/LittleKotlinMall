package mall.kotlin.com.goodscenter.service.impl

import mall.kotlin.com.baselibrary.ext.convert
import mall.kotlin.com.goodscenter.data.protocol.Category
import mall.kotlin.com.goodscenter.data.repository.CategoryRepository
import mall.kotlin.com.goodscenter.service.CategoryService
import rx.Observable
import javax.inject.Inject

/**
 *    author : zhangkun .
 *    date   : on 2018/10/25
 */
class CategoryServiceImpl @Inject constructor() : CategoryService {

    @Inject
    lateinit var repository: CategoryRepository

    /**
     * 获取分类内容
     */
    override fun getCategory(parentId: Int): Observable<MutableList<Category>?> {

        return repository.getCategory(parentId)
                .convert()
    }
}