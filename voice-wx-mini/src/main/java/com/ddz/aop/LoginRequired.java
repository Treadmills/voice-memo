package com.ddz.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by admin on 2019/11/20.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface LoginRequired {

    /**
     * 是否需要登录，缺省为需要
     *
     * @return
     */
    boolean loginRequired() default true;
}
