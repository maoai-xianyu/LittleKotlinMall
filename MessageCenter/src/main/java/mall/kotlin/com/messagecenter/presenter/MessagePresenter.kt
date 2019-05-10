package mall.kotlin.com.messagecenter.presenter

import mall.kotlin.com.baselibrary.ext.execute
import mall.kotlin.com.baselibrary.presenter.BasePresenter
import mall.kotlin.com.baselibrary.rx.BaseSubscriber
import mall.kotlin.com.messagecenter.data.protocol.Message
import mall.kotlin.com.messagecenter.presenter.view.MessageView
import mall.kotlin.com.messagecenter.service.MessageService
import javax.inject.Inject

/**
 * author:    zhangkun .
 * date:    on 2019-05-10.
 */
class MessagePresenter @Inject constructor() : BasePresenter<MessageView>() {


    @Inject
    lateinit var messageService: MessageService

    fun getMessageList() {

        /**
         * 业务逻辑
         */
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        messageService.getMessageList()
                .execute(object : BaseSubscriber<MutableList<Message>?>(mView) {
                    override fun onNext(t: MutableList<Message>?) {
                        mView.onGetMessageListResult(t)
                    }
                }, lifecycleProvider)
    }
}