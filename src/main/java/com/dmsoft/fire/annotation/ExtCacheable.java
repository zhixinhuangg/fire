package com.dmsoft.fire.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ExtCacheable {

    String key() default "";

    String nextKey() default "";

    int expireTime() default 1800;
}
