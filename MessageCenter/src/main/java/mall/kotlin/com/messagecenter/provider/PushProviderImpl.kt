package mall.kotlin.com.messagecenter.provider

import android.content.Context
import cn.jpush.android.api.JPushInterface
import com.alibaba.android.arouter.facade.annotation.Route
import mall.kotlin.com.provider.PushProvider
import mall.kotlin.com.provider.router.RouterPath

/**
 * author:    zhangkun .
 * date:    on 2019-05-10.
 */
@Route(path = RouterPath.MessageCenter.PATH_MESSAGE_PUSH)
class PushProviderImpl : PushProvider {

    private var mContext: Context? = null
    override fun getPushId(): String {
        return JPushInterface.getRegistrationID(mContext)
    }

    override fun init(context: Context?) {
        mContext = context
    }
}