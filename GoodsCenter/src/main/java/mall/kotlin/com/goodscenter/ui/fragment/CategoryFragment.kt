package mall.kotlin.com.goodscenter.ui.fragment

import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import com.kennyc.view.MultiStateView
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.fragment_category.*
import mall.kotlin.com.baselibrary.ui.adapter.BaseRecyclerViewAdapter
import mall.kotlin.com.baselibrary.ui.fragment.BaseMvpFragment
import mall.kotlin.com.goodscenter.R
import mall.kotlin.com.goodscenter.data.protocol.Category
import mall.kotlin.com.goodscenter.injection.component.DaggerCategoryComponent
import mall.kotlin.com.goodscenter.injection.module.CategoryModule
import mall.kotlin.com.goodscenter.presenter.CategoryPresenter
import mall.kotlin.com.goodscenter.presenter.view.CategoryView
import mall.kotlin.com.goodscenter.ui.adapter.SecondCategoryAdapter
import mall.kotlin.com.goodscenter.ui.adapter.TopCategoryAdapter

/**
 * authorhangkun .
 * date:    on 2019/3/11.
 */
class CategoryFragment : BaseMvpFragment<CategoryPresenter>(), CategoryView {

    private val topAdapter by lazy {
        TopCategoryAdapter(context!!)
    }

    private val secondAdapter by lazy {
        SecondCategoryAdapter(context!!)
    }

    override fun setView(): Int {
        return R.layout.fragment_category
    }

    override fun initView() {
        mTopCategoryRv.layoutManager = LinearLayoutManager(context)
        mTopCategoryRv.adapter = topAdapter

        mSecondCategoryRv.layoutManager = GridLayoutManager(context, 3)
        mSecondCategoryRv.adapter = secondAdapter

    }

    override fun setListener() {
        topAdapter.setOnItemClickListener(object : BaseRecyclerViewAdapter.OnItemClickListener<Category> {
            override fun onItemClick(item: Category, position: Int) {
                for (category in topAdapter.dataList) {
                    category.isSelected = item.id == category.id
                }
                topAdapter.notifyDataSetChanged()
                loadData(item.id)
            }
        })

        secondAdapter.setOnItemClickListener(object : BaseRecyclerViewAdapter.OnItemClickListener<Category> {
            override fun onItemClick(item: Category, position: Int) {
                Logger.d("点击")

            }
        })

    }

    override fun start() {
        loadData()
    }

    private fun loadData(parentId:Int = 0){
        mPresenter.getCategory(parentId)

    }

    override fun injectComponent() {
        DaggerCategoryComponent.builder().activityComponent(activityComponent)
                .categoryModule(CategoryModule())
                .build()
                .inject(this)

        mPresenter.mView = this
    }

    override fun onGetCategoryResult(result: MutableList<Category>?) {

        result?.let {
            if (result[0].parentId == 0) {
                result[0].isSelected = true
                topAdapter.setData(result)
                loadData(result[0].id)
            } else {
                secondAdapter.setData(result)
                mMultiStateView.viewState = MultiStateView.VIEW_STATE_CONTENT
            }
        }

    }
}