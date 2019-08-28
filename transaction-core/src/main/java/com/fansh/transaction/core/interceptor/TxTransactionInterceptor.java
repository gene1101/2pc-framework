package com.fansh.transaction.core.interceptor;

import org.aspectj.lang.ProceedingJoinPoint;

public interface TxTransactionInterceptor {

    Object interceptor(ProceedingJoinPoint proceedingJoinPoint) throws Throwable;
}
