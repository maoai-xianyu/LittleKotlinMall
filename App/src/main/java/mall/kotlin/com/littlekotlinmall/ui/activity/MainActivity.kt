package mall.kotlin.com.littlekotlinmall.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.activity_main.*
import mall.kotlin.com.littlekotlinmall.R
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mBottomNavBar.checkCartBadge(20)
        mBottomNavBar.checkMsgBadge(false)

        Observable.timer(2, TimeUnit.SECONDS).observeOn(AndroidSchedulers.mainThread())
                .subscribe({

                    mBottomNavBar.checkMsgBadge(true)

                }, {
                    Logger.e(it.localizedMessage)
                })


        Observable.timer(5, TimeUnit.SECONDS).observeOn(AndroidSchedulers.mainThread())
                .subscribe({

                    mBottomNavBar.checkCartBadge(0)

                }, {
                    Logger.e(it.localizedMessage)

                })
    }
}
