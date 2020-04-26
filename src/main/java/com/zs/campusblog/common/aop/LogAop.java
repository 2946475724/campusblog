package com.zs.campusblog.common.aop;

import java.lang.annotation.*;

/**
 * 日志注解，作用于方法上，并且在运行时有效
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogAop {
    String value() default "";
}
