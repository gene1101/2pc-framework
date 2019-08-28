package com.fansh.transaction.core.utils;

import com.fansh.transaction.core.handler.impl.StartTxTransactionHandler;
import com.fansh.transaction.core.idGenerator.impl.TransactionIdGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IdUtils {

    private static final Logger logger= LoggerFactory.getLogger(IdUtils.class);


    private static TransactionIdGenerator transactionIdGenerator;

    static {
        try {
            transactionIdGenerator = new TransactionIdGenerator(2500, 0.8f);
        } catch (Exception e) {
            logger.error("init id generator error,e:"+e);
        }
    }

    public static String getId(String businessKey){
       return transactionIdGenerator.getNextId(businessKey);
    }
}
