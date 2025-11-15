package com.wlog.wlogcommon.aspect;

import java.lang.annotation.*;

/**
 * @author： wsw
 * @date： 2025/11/15 11:13
 * @describe：
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface ApiOperationLog {
    /**
     * API 功能描述
     *
     * @return
     */
    String description() default "";

}
