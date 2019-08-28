package com.fansh.transaction.core.handler.factory.intf;


import com.fansh.transaction.common.bean.TransactionInfo;
import com.fansh.transaction.core.handler.intf.TxTransactionHandler;

public interface TxTransactionHandlerFactory {

    TxTransactionHandler getTxTransactionHandler(TransactionInfo transactionInfo);

}
