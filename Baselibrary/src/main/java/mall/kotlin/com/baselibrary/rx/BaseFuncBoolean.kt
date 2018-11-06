package mall.kotlin.com.baselibrary.rx

import mall.kotlin.com.baselibrary.data.protocol.BaseResp
import rx.Observable
import rx.functions.Func1


/**
 * author:  zhangkun .
 * date:    on 2018/11/6.
 * 用于返回结果返回为boolean类型的
 */
class BaseFuncBoolean<T> : Func1<BaseResp<T>, Observable<Boolean>> {
    override fun call(t: BaseResp<T>): Observable<Boolean> {
        if (t.status != 0) {
            return Observable.error(BaseException(t.status, t.message))
        }
        return Observable.just(true)
    }

}