package com.fansh.transaction.core.handler.impl;

import com.fansh.transaction.commnuication.notify.TransactionManagerMessageService;
import com.fansh.transaction.common.bean.TransactionInfo;
import com.fansh.transaction.core.concurrent.threadLocal.TxThreadLocal;
import com.fansh.transaction.core.handler.factory.impl.TxTransactionHandlerFactoryImpl;
import com.fansh.transaction.core.handler.intf.TxTransactionHandler;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;

import javax.annotation.Resource;


@Service
public class SlaveTxTransactionHandler implements TxTransactionHandler {


    private static final Logger logger= LoggerFactory.getLogger(SlaveTxTransactionHandler.class);


    @Resource
    private PlatformTransactionManager platformTransactionManager;


    @Autowired
    private TransactionManagerMessageService transactionManagerMessageService;

    @Override
    public Object handle(ProceedingJoinPoint proceedingJoinPoint, TransactionInfo transactionInfo) {


        return null;
    }
}
