package mall.kotlin.com.provider.router

/**
 * author:  zhangkun .
 * date:    on 2018/10/25.
 */

object RouterPath {

    class UserCenter {
        companion object {
            const val PATH_LOGIN = "/userCenter/login"
        }
    }

    class OrderCenter {
        companion object {
            const val PATH_ORDER_CONFIRM = "/orderCenter/confirm"
        }
    }

    class PaySDK {
        companion object {
            const val PATH_PAY= "/paySDK/pay"
        }
    }

    class MessageCenter{
        companion object{
            const val PATH_MESSAGE_PUSH= "/messageCenter/push"
        }
    }


    class MessageCenterOrder{
        companion object{
            const val PATH_MESSAGE_ORDER= "/MessageCenterOrder/order"
        }
    }


}