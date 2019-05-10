package mall.kotlin.com.messagecenter.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import cn.jpush.android.api.JPushInterface
import com.alibaba.android.arouter.launcher.ARouter
import com.eightbitlab.rxbus.Bus
import mall.kotlin.com.provider.common.ProviderConstant
import mall.kotlin.com.provider.event.MessageBadgeEvent
import mall.kotlin.com.provider.router.RouterPath
import org.json.JSONObject
import timber.log.Timber


/*
    自定义Push 接收器
 */
class MessageReceiver : BroadcastReceiver() {
    val TAG = "MessageReceiver"
    override fun onReceive(context: Context, intent: Intent) {

        val bundle = intent.extras
        Timber.d(TAG + "onReceive - " + intent.action + ", extras: " + bundle)

        when {
            JPushInterface.ACTION_REGISTRATION_ID == intent.action -> Timber.d(TAG + "JPush用户注册成功")
            JPushInterface.ACTION_MESSAGE_RECEIVED == intent.action -> {
                Timber.d(TAG + "接受到推送下来的自定义消息")
                Bus.send(MessageBadgeEvent(true))

                Toast.makeText(context, bundle.getString(JPushInterface.EXTRA_MESSAGE), Toast.LENGTH_LONG).show()

            }
            JPushInterface.ACTION_NOTIFICATION_RECEIVED == intent.action -> {
                Timber.d(TAG + "接受到推送下来的通知")
                Toast.makeText(context, "接受到推送下来的通知" + bundle.getString(JPushInterface.EXTRA_NOTIFICATION_TITLE), Toast.LENGTH_LONG).show()
            }
            JPushInterface.ACTION_NOTIFICATION_OPENED == intent.action -> {
                Timber.d(TAG + "用户点击打开了通知")
                val extra = bundle.getString(JPushInterface.EXTRA_EXTRA)
                val json = JSONObject(extra)
                val orderId = json.getInt("orderId")
                ARouter.getInstance().build(RouterPath.MessageCenter.PATH_MESSAGE_ORDER)
                        .withInt(ProviderConstant.KEY_ORDER_ID, orderId)
                        .navigation()

            }
            else -> Timber.d(TAG + "Unhandled intent - " + intent.action)
        }
    }
}
