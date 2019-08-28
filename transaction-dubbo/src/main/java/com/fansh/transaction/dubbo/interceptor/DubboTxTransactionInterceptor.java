package com.fansh.transaction.dubbo.interceptor;


import com.alibaba.dubbo.rpc.RpcContext;
import com.fansh.transaction.common.constants.TxConstants;
import com.fansh.transaction.core.aop.invoke.intf.TransactionInvoker;
import com.fansh.transaction.core.interceptor.TxTransactionInterceptor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service("dubboTxTransactionInterceptor")
public class DubboTxTransactionInterceptor implements TxTransactionInterceptor {


    @Resource
    private  TransactionInvoker transactionInvoker;

    @Override
    public Object interceptor(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        String groupId= RpcContext.getContext().getAttachment(TxConstants.TRANSACTION_GROUP);

        return transactionInvoker.invoke(groupId,proceedingJoinPoint);
    }
}
