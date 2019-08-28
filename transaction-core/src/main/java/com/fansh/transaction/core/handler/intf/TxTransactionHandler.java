package com.fansh.transaction.core.handler.intf;

import com.fansh.transaction.common.bean.TransactionInfo;
import org.aspectj.lang.ProceedingJoinPoint;

public interface TxTransactionHandler {


    Object handle(ProceedingJoinPoint proceedingJoinPoint, TransactionInfo transactionInfo) throws Throwable;
}
