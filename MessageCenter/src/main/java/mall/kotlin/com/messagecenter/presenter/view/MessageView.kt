package mall.kotlin.com.messagecenter.presenter.view

import mall.kotlin.com.baselibrary.presenter.view.BaseView
import mall.kotlin.com.messagecenter.data.protocol.Message

/**
 * author:    zhangkun .
 * date:    on 2019-05-10.
 */
interface MessageView : BaseView {

    fun onGetMessageListResult(result: MutableList<Message>?)
}