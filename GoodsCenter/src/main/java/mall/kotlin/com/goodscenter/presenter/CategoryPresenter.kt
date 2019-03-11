package mall.kotlin.com.goodscenter.presenter

import mall.kotlin.com.baselibrary.ext.execute
import mall.kotlin.com.baselibrary.presenter.BasePresenter
import mall.kotlin.com.baselibrary.rx.BaseSubscriber
import mall.kotlin.com.goodscenter.data.protocol.Category
import mall.kotlin.com.goodscenter.presenter.view.CategoryView
import mall.kotlin.com.goodscenter.service.CategoryService
import javax.inject.Inject

/**
 * author:  zhangkun .
 * date:    on 2018/10/25.
 */
class CategoryPresenter @Inject constructor() : BasePresenter<CategoryView>() {

    @Inject
    lateinit var categoryService: CategoryService

    fun getCategory(parentId: Int) {

        /**
         * 业务逻辑
         */
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        categoryService.getCategory(parentId)
                .execute(object : BaseSubscriber<MutableList<Category>?>(mView) {
                    override fun onNext(t: MutableList<Category>?) {
                        mView.onGetCategoryResult(t)
                    }
                }, lifecycleProvider)
    }

}