package com.fansh.transaction.core.aop.invoke.aspect;

import com.fansh.transaction.core.interceptor.TxTransactionInterceptor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public abstract class AbstractTxTransactionAspect {

    private TxTransactionInterceptor transactionInterceptor;


    public void setTransactionInterceptor(TxTransactionInterceptor transactionInterceptor){
        this.transactionInterceptor=transactionInterceptor;
    }


    @Pointcut("@annotation(com.fansh.transaction.core.annotation.TxTransaction)")
    public void txTransactionInterpector(){

    }

    @Around("txTransactionInterpector()")
    public Object interceptor(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        return transactionInterceptor.interceptor(proceedingJoinPoint);
    }

    /**
     * 设置优先级
     * @return
     */
    public abstract int getOrder();
}
