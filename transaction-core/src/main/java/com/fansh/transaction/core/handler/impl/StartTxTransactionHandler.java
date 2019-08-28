package com.fansh.transaction.core.handler.impl;

import com.fansh.transaction.commnuication.message.SingleTransaction;
import com.fansh.transaction.commnuication.message.TxTransactionGroup;
import com.fansh.transaction.commnuication.notify.TransactionManagerMessageService;
import com.fansh.transaction.common.bean.TransactionInfo;
import com.fansh.transaction.common.constants.TxConstants;
import com.fansh.transaction.common.enums.PropagationEnum;
import com.fansh.transaction.common.enums.TxTransactionRole;
import com.fansh.transaction.common.enums.TxTransactionStatus;
import com.fansh.transaction.common.exception.TxTransactionException;
import com.fansh.transaction.core.concurrent.threadLocal.TxThreadLocal;
import com.fansh.transaction.core.handler.intf.TxTransactionHandler;
import com.fansh.transaction.core.utils.IdUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * 分布式事物发起处理器
 */
@Service
public class StartTxTransactionHandler implements TxTransactionHandler {

    private static final Logger logger= LoggerFactory.getLogger(StartTxTransactionHandler.class);

    @Autowired
    private  PlatformTransactionManager platformTransactionManager;


    @Autowired
    private TransactionManagerMessageService transactionManagerMessageService;


    @Override
    public Object handle(ProceedingJoinPoint proceedingJoinPoint, TransactionInfo transactionInfo) throws Throwable {

        logger.info("tx-transaction begin,transaction begin class:"+transactionInfo.getInvocation().getTargetClass());


        final String txGroupId= IdUtils.getId(TxConstants.ID_TRANSACTION_GROUP_START);

        TxThreadLocal.getInstance().setTxGroupId(txGroupId);

        final String waitTaskKey=IdUtils.getId(TxConstants.TASK_TRANSACTION_START);

        final boolean success=transactionManagerMessageService.createTxTransactionGroupInfo(newTxTransactionGroup(txGroupId,transactionInfo));
            if (success){

                /**
                 * 无事物情况，直接提交
                 */
                if (transactionInfo.getPropagationEnum().value()== PropagationEnum.NEVER.value()){
                    try {
                        final Object result=proceedingJoinPoint.proceed();

                        final boolean commit=transactionManagerMessageService.preCommitTxTransaction(txGroupId);

                        if (commit){

                            transactionManagerMessageService.commitTxTransactionGroup(txGroupId);

                        }
                        return result;
                    } catch (Throwable throwable) {
                        logger.error("aop proceed error:"+throwable.getCause());
                        transactionManagerMessageService.rollBackTxTransactionGroup(txGroupId);
                        throw throwable;
                    }
                    finally {
                        TxThreadLocal.getInstance().removeTxGroupId();
                    }
                }
                else{
                    DefaultTransactionDefinition defaultTransactionDefinition=new DefaultTransactionDefinition();

                    defaultTransactionDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);

                    TransactionStatus transactionStatus=platformTransactionManager.getTransaction(defaultTransactionDefinition);

                    try{
                        final Object result=proceedingJoinPoint.proceed();

                        final boolean commit=transactionManagerMessageService.preCommitTxTransaction(txGroupId);

                        if (commit){

                            transactionManagerMessageService.commitTxTransactionGroup(txGroupId);

                            platformTransactionManager.commit(transactionStatus);
                        }
                        else{
                            logger.error("预提交失败");

                            transactionManagerMessageService.rollBackTxTransactionGroup(txGroupId);

                            platformTransactionManager.rollback(transactionStatus);

                        }
                        logger.info("tx-transaction end，事物发起类："+transactionInfo.getInvocation().getTargetClass());

                        return result;
                    }
                    catch (Throwable throwable){
                        logger.error("aop proceed error:"+throwable.getCause());
                        platformTransactionManager.rollback(transactionStatus);
                        transactionManagerMessageService.rollBackTxTransactionGroup(txGroupId);
                        throw throwable;
                    }
                    finally {
                        TxThreadLocal.getInstance().removeTxGroupId();
                    }
                }
            }
            else{
                throw new TxTransactionException("连接分布式事物管理器失败");
            }
    }


    private TxTransactionGroup newTxTransactionGroup(String txGroupId,TransactionInfo transactionInfo){

        TxTransactionGroup txTransactionGroup=new TxTransactionGroup();

        txTransactionGroup.setId(txGroupId);

        txTransactionGroup.setStatus(TxTransactionStatus.BEGIN.getCode());

        List<SingleTransaction> singleTransactionList=new ArrayList<SingleTransaction>();

        SingleTransaction singleTransaction=new SingleTransaction();
        singleTransaction.setCreateDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        singleTransaction.setTxId(IdUtils.getId(TxConstants.ID_TRANSACTION_START));
        try {
            String macIp=InetAddress.getLocalHost().getHostAddress().toString();
            singleTransaction.setIp(macIp);
        } catch (UnknownHostException e) {
            logger.error("error message,e:"+e.getCause());
        }
        singleTransaction.setStatus(TxTransactionStatus.BEGIN.getCode());
        singleTransaction.setTargetClass(transactionInfo.getInvocation().getTargetClass().getName());
        singleTransaction.setTargetMethod(transactionInfo.getInvocation().getMethod());
        singleTransaction.setRole(TxTransactionRole.STARTER.getCode());

        singleTransactionList.add(singleTransaction);

        txTransactionGroup.setSingleTransactionList(singleTransactionList);

        return txTransactionGroup;
    }
}
