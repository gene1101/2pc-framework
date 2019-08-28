package com.fansh.transaction.commnuication.notify;

import com.fansh.transaction.commnuication.message.SingleTransaction;
import com.fansh.transaction.commnuication.message.TxTransactionGroup;

public interface TransactionManagerMessageService {

    /**
     * 创建分布式事物组
     * @param txTransactionGroup 分布式事物组信息
     * @return
     */
     boolean createTxTransactionGroupInfo(TxTransactionGroup txTransactionGroup);

    /**
     * 加入分布式事物组
     * @param txGroupId 分布式事物组id
     * @param singleTransaction 单个事物信息
     * @return
     */
     boolean addTxTransactionRecord(String txGroupId, SingleTransaction singleTransaction);


    /**
     * 提交分布式事物
     * @param txGroupId 分布式组id
     * @return
     */
    boolean commitTxTransactionGroup(String txGroupId);

    /**
     * 预提交分布式事物
     * @param txGroupId 分布式组id
     * @return
     */
    boolean preCommitTxTransaction(String txGroupId);

    /**
     * 回滚分布式事物
     * @param txGroupId 分布式事物组id
     * @return
     */
    boolean rollBackTxTransactionGroup(String txGroupId);
}
