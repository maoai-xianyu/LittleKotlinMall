package mall.kotlin.com.goodscenter.ui.activity

import android.support.v7.widget.GridLayoutManager
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder
import cn.bingoogolapple.refreshlayout.BGARefreshLayout
import com.kennyc.view.MultiStateView
import kotlinx.android.synthetic.main.activity_goods.*
import mall.kotlin.com.baselibrary.BaseApplication.Companion.context
import mall.kotlin.com.baselibrary.ext.startLoading
import mall.kotlin.com.baselibrary.ui.activity.BaseMvpActivity
import mall.kotlin.com.baselibrary.ui.adapter.BaseRecyclerViewAdapter
import mall.kotlin.com.goodscenter.R
import mall.kotlin.com.goodscenter.common.GoodsConstant
import mall.kotlin.com.goodscenter.data.protocol.Goods
import mall.kotlin.com.goodscenter.injection.component.DaggerGoodsComponent
import mall.kotlin.com.goodscenter.injection.module.GoodsModule
import mall.kotlin.com.goodscenter.presenter.GoodsPresenter
import mall.kotlin.com.goodscenter.presenter.view.GoodsView
import mall.kotlin.com.goodscenter.ui.adapter.GoodsAdapter
import org.jetbrains.anko.startActivity

/**
 * author:  zhangkun .
 * date:    on 2019/3/12.
 */
class GoodsActivity : BaseMvpActivity<GoodsPresenter>(), GoodsView, BGARefreshLayout.BGARefreshLayoutDelegate {

    private val goodsAdapter by lazy {
        GoodsAdapter(context)
    }
    private var mCurrentPage: Int = 1
    private var mMaxPage: Int = 1
    private var mData: MutableList<Goods>? = null

    override fun setView(): Int {
        return R.layout.activity_goods
    }

    override fun initView() {
        mGoodsRv.layoutManager = GridLayoutManager(context, 2)
        mGoodsRv.adapter = goodsAdapter

        // 设置上拉加载和下拉刷新
        mRefreshLayout.setDelegate(this)
        // 设置下拉刷新和上拉加载更多的风格
        val refreshViewHolder = BGANormalRefreshViewHolder(this, true)

        refreshViewHolder.setLoadMoreBackgroundColorRes(R.color.common_bg)
        refreshViewHolder.setRefreshViewBackgroundColorRes(R.color.common_bg)
        mRefreshLayout.setRefreshViewHolder(refreshViewHolder)

    }

    override fun start() {
        mMultiStateView.startLoading()
        if (intent.getIntExtra(GoodsConstant.KEY_SEARCH_GOODS_TYPE, 0) != 0) {
            mPresenter.getGoodsListByKeyword(intent.getStringExtra(GoodsConstant.KEY_GOODS_KEYWORD), mCurrentPage)
        } else {
            mPresenter.getGoodsList(intent.getIntExtra(GoodsConstant.KEY_CATEGORY_ID, 1), mCurrentPage)
        }
    }

    override fun setListener() {
        goodsAdapter.setOnItemClickListener(object : BaseRecyclerViewAdapter.OnItemClickListener<Goods> {
            override fun onItemClick(item: Goods, position: Int) {
                startActivity<GoodsDetailActivity>(GoodsConstant.KEY_GOODS_ID to item.id)
            }
        })

    }

    override fun injectComponent() {
        DaggerGoodsComponent.builder()
                .activityComponent(activityComponent)
                .goodsModule(GoodsModule())
                .build()
                .inject(this)

        mPresenter.mView = this

    }


    override fun onGetGoodsListResult(result: MutableList<Goods>?) {
        mRefreshLayout.endLoadingMore()
        mRefreshLayout.endRefreshing()
        if (result != null && result.size > 0) {
            mMaxPage = result[0].maxPage
            if (mCurrentPage == 1) {
                goodsAdapter.setData(result)
            } else {
                goodsAdapter.dataList.addAll(result)
                goodsAdapter.notifyDataSetChanged()
            }
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_CONTENT
        } else {
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_EMPTY

        }
    }


    override fun onBGARefreshLayoutBeginLoadingMore(refreshLayout: BGARefreshLayout?): Boolean {
        return if (mCurrentPage < mMaxPage) {
            mCurrentPage++
            start()
            true
        } else {
            false
        }
    }

    override fun onBGARefreshLayoutBeginRefreshing(refreshLayout: BGARefreshLayout?) {
        mCurrentPage = 1
        start()
    }

}