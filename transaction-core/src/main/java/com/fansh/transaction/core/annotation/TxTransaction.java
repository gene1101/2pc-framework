package com.fansh.transaction.core.annotation;


import com.fansh.transaction.common.enums.PropagationEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface TxTransaction {

    PropagationEnum propagation() default PropagationEnum.REQUIRES_NEW;

    /**
     *
     * 事物最大等待时间
     * @return 秒
     */
    int waitMaxTime() default 60;
}
