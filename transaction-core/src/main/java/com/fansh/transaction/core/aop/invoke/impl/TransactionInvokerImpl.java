package com.fansh.transaction.core.aop.invoke.impl;

import com.fansh.transaction.common.bean.TransactionInfo;
import com.fansh.transaction.common.bean.TransactionInvocation;
import com.fansh.transaction.common.enums.PropagationEnum;
import com.fansh.transaction.core.annotation.TxTransaction;
import com.fansh.transaction.core.aop.invoke.intf.TransactionInvoker;
import com.fansh.transaction.core.handler.factory.intf.TxTransactionHandlerFactory;
import com.fansh.transaction.core.handler.intf.TxTransactionHandler;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.UUID;


@Service
public class TransactionInvokerImpl implements TransactionInvoker {

    @Resource
    private TxTransactionHandlerFactory txTransactionHandlerFactory;


    public Object invoke(String transactionGroupId, ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        MethodSignature methodSignature=(MethodSignature) proceedingJoinPoint.getSignature();

        Method method=methodSignature.getMethod();

        Class<?> targetClazz=proceedingJoinPoint.getTarget().getClass();

        Object[] args=proceedingJoinPoint.getArgs();

        String transactionId= UUID.randomUUID().toString();

        final TxTransaction txTransaction=method.getAnnotation(TxTransaction.class);

        final int waitTime=txTransaction.waitMaxTime();

        final PropagationEnum propagationEnum=txTransaction.propagation();


        TransactionInvocation transactionInvocation=new TransactionInvocation(method.getName(),args,method.getParameterTypes(),targetClazz);

        TransactionInfo transactionInfo=new TransactionInfo(transactionInvocation,transactionGroupId,transactionId,waitTime,propagationEnum);

        TxTransactionHandler txTransactionHandler=txTransactionHandlerFactory.getTxTransactionHandler(transactionInfo);


        return txTransactionHandler.handle(proceedingJoinPoint,transactionInfo);
    }
}
