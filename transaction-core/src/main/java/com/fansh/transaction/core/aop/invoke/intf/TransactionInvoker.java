package com.fansh.transaction.core.aop.invoke.intf;

import org.aspectj.lang.ProceedingJoinPoint;

public interface TransactionInvoker {

    /**
     * 事物切面
     * @param transactionGroupId 事物组id
     * @param proceedingJoinPoint 切点
     * @return
     * @throws Throwable 异常
     */
    Object invoke(String transactionGroupId, ProceedingJoinPoint proceedingJoinPoint) throws Throwable;
}
