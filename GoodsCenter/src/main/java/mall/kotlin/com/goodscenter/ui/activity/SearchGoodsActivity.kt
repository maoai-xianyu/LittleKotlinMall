package mall.kotlin.com.goodscenter.ui.activity

import android.support.v7.widget.LinearLayoutManager
import android.view.View
import kotlinx.android.synthetic.main.activity_search_goods.*
import mall.kotlin.com.baselibrary.BaseApplication.Companion.context
import mall.kotlin.com.baselibrary.ext.onClick
import mall.kotlin.com.baselibrary.ext.setVisible
import mall.kotlin.com.baselibrary.ui.activity.BaseUIActivity
import mall.kotlin.com.baselibrary.ui.adapter.BaseRecyclerViewAdapter
import mall.kotlin.com.baselibrary.utils.AppPrefsUtils
import mall.kotlin.com.goodscenter.R
import mall.kotlin.com.goodscenter.common.GoodsConstant
import mall.kotlin.com.goodscenter.ui.adapter.SearchHistoryAdapter
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

/*
    关键字搜索商品页
 */
class SearchGoodsActivity : BaseUIActivity(), View.OnClickListener {
    private val mAdapter by lazy {
        SearchHistoryAdapter(context)
    }

    override fun setView(): Int {
        return R.layout.activity_search_goods
    }


    override fun setListener() {
        mLeftIv.onClick(this)
        mSearchTv.onClick(this)
        mClearBtn.onClick(this)
        //item点击事件
        mAdapter.mItemClickListener = object : BaseRecyclerViewAdapter.OnItemClickListener<String> {
            override fun onItemClick(item: String, position: Int) {
                enterGoodsList(item)
            }
        }
    }

    override fun start() {
        loadData()
    }

    /**
     * 初始化视图
     */
    override fun initView() {
        //RecyclerView适配器
        mSearchHistoryRv.layoutManager = LinearLayoutManager(this)
        mSearchHistoryRv.adapter = mAdapter

    }

    /*
        从SP中加载数据
     */
    private fun loadData() {
        val set = AppPrefsUtils.getStringSet(GoodsConstant.SP_SEARCH_HISTORY)
        mNoDataTv.setVisible(set.isEmpty())
        mDataView.setVisible(set.isNotEmpty())
        if (set.isNotEmpty()) {
            val list = mutableListOf<String>()
            list.addAll(set)
            mAdapter.setData(list)
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.mLeftIv -> finish()
            //执行搜索
            R.id.mSearchTv -> doSearch()
            //清除历史记录
            R.id.mClearBtn -> {
                AppPrefsUtils.remove(GoodsConstant.SP_SEARCH_HISTORY)
                loadData()
            }
        }
    }

    //搜索
    private fun doSearch() {
        if (mKeywordEt.text.isNullOrEmpty()) {
            toast("请输入需要搜索的关键字")
        } else {
            val inputValue = mKeywordEt.text.toString()
            AppPrefsUtils.putStringSet(GoodsConstant.SP_SEARCH_HISTORY, mutableSetOf(inputValue))
            enterGoodsList(inputValue)
        }
    }

    /*
        进入商品列表界面
     */
    private fun enterGoodsList(value: String) {
        //输入不为空，进入商品列表
        startActivity<GoodsActivity>(
                GoodsConstant.KEY_SEARCH_GOODS_TYPE to GoodsConstant.SEARCH_GOODS_TYPE_KEYWORD,
                GoodsConstant.KEY_GOODS_KEYWORD to value
        )

    }
}
