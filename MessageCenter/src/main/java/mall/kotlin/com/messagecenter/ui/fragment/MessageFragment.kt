package mall.kotlin.com.messagecenter.ui.fragment

import android.support.v7.widget.LinearLayoutManager
import com.eightbitlab.rxbus.Bus
import com.kennyc.view.MultiStateView
import kotlinx.android.synthetic.main.fragment_message.*
import mall.kotlin.com.baselibrary.ext.startLoading
import mall.kotlin.com.baselibrary.ui.fragment.BaseMvpFragment
import mall.kotlin.com.messagecenter.R
import mall.kotlin.com.messagecenter.data.protocol.Message
import mall.kotlin.com.messagecenter.injection.component.DaggerMessageComponent
import mall.kotlin.com.messagecenter.injection.module.MessageModule
import mall.kotlin.com.messagecenter.presenter.MessagePresenter
import mall.kotlin.com.messagecenter.presenter.view.MessageView
import mall.kotlin.com.messagecenter.ui.adapter.MessageAdapter
import mall.kotlin.com.provider.event.MessageBadgeEvent

/**
 * author:    zhangkun .
 * date:    on 2019-05-10.
 */

class MessageFragment : BaseMvpFragment<MessagePresenter>(), MessageView {


    private lateinit var mAdapter: MessageAdapter
    override fun setView(): Int {
        return R.layout.fragment_message
    }

    override fun initView() {
        mMessageRv.layoutManager = LinearLayoutManager(context)
        mAdapter = MessageAdapter(context!!)
        mMessageRv.adapter = mAdapter

    }

    override fun start() {
        mMultiStateView.startLoading()
        mPresenter.getMessageList()

    }


    override fun injectComponent() {
        DaggerMessageComponent.builder().activityComponent(activityComponent)
                .messageModule(MessageModule())
                .build()
                .inject(this)
        mPresenter.mView = this

    }


    override fun onGetMessageListResult(result: MutableList<Message>?) {

        if (result != null && result.size > 0) {
            mAdapter.dataList.addAll(result)
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_CONTENT
        } else {
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_EMPTY
        }
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (!hidden) {
            Bus.send(MessageBadgeEvent(false))


        }
    }
}