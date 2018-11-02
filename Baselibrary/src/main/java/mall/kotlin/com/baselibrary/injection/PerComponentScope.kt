package mall.kotlin.com.baselibrary.injection

import java.lang.annotation.Documented
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy.RUNTIME
import javax.inject.Scope

/**
 * author:  zhangkun .
 * date:    on 2018/11/2.
 */
@Scope
@Documented
@Retention(RUNTIME)
annotation class PerComponentScope