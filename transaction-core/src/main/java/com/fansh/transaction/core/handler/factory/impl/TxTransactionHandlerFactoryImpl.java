package com.fansh.transaction.core.handler.factory.impl;

import com.fansh.transaction.common.bean.TransactionInfo;
import com.fansh.transaction.core.handler.factory.intf.TxTransactionHandlerFactory;
import com.fansh.transaction.core.handler.impl.SlaveTxTransactionHandler;
import com.fansh.transaction.core.handler.impl.StartTxTransactionHandler;
import com.fansh.transaction.core.handler.intf.TxTransactionHandler;
import com.fansh.transaction.core.utils.SpringBeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class TxTransactionHandlerFactoryImpl implements TxTransactionHandlerFactory {

    private static final Logger logger= LoggerFactory.getLogger(TxTransactionHandlerFactoryImpl.class);


    @Override
    public TxTransactionHandler getTxTransactionHandler(TransactionInfo transactionInfo) {

        TxTransactionHandler txTransactionHandler=null;

        Class handlerClass=judgeHandler(transactionInfo);

        Object object= SpringBeanUtils.getBeanByTypte(handlerClass);

        if (object==null){
            try {
                txTransactionHandler = (TxTransactionHandler) handlerClass.newInstance();
            } catch (InstantiationException e) {
                logger.error("create instance error,InstantiationException:"+e);

            } catch (IllegalAccessException e) {
                logger.error("create instance error,IllegalAccessException:"+e);
            }
        }
        else{
            txTransactionHandler=(TxTransactionHandler) object;
        }

        return txTransactionHandler;
    }



    private Class judgeHandler(TransactionInfo transactionInfo){

        if (transactionInfo.getTransactionGroupId()==null || transactionInfo.getTransactionGroupId().equals("")){
            return StartTxTransactionHandler.class;
        }
        else{

            return SlaveTxTransactionHandler.class;
        }

    }
}
