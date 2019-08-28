package com.fansh.transaction.commnuication.notify.zookeeper;

import com.fansh.transaction.commnuication.message.SingleTransaction;
import com.fansh.transaction.commnuication.message.TxTransactionGroup;
import com.fansh.transaction.commnuication.notify.TransactionManagerMessageService;
import com.fansh.transaction.commnuication.notify.rocketmq.RocketMqTransactionMessageServiceImpl;
import org.I0Itec.zkclient.ZkClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ZookeeperTransactionMessageServiceImpl implements TransactionManagerMessageService {


    private static final Logger logger= LoggerFactory.getLogger(RocketMqTransactionMessageServiceImpl.class);

    @Override
    public boolean createTxTransactionGroupInfo(TxTransactionGroup txTransactionGroup) {

        return false;
    }

    @Override
    public boolean addTxTransactionRecord(String txGroupId, SingleTransaction singleTransaction) {
        return false;
    }

    @Override
    public boolean commitTxTransactionGroup(String txGroupId) {
        return false;
    }

    @Override
    public boolean preCommitTxTransaction(String txGroupId) {
        return false;
    }

    @Override
    public boolean rollBackTxTransactionGroup(String txGroupId) {
        return false;
    }
}
