package mall.kotlin.com.baselibrary.rx

import mall.kotlin.com.baselibrary.data.protocol.BaseResp
import rx.Observable
import rx.functions.Func1


/**
 * author:  zhangkun .
 * date:    on 2018/11/6.
 * 用于返回数据类型的结果
 */
class BaseFunc<T> : Func1<BaseResp<T>, Observable<T>> {
    override fun call(t: BaseResp<T>): Observable<T> {
        if (t.status != 0) {
            return Observable.error(BaseException(t.status, t.message))
        }
        return Observable.just(t.data)
    }

}