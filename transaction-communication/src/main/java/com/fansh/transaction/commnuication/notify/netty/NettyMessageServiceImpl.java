package com.fansh.transaction.commnuication.notify.netty;

import com.fansh.transaction.commnuication.message.SingleTransaction;
import com.fansh.transaction.commnuication.message.TxTransactionGroup;
import com.fansh.transaction.commnuication.message.TxTransactionMessage;
import com.fansh.transaction.commnuication.notify.TransactionManagerMessageService;
import com.fansh.transaction.commnuication.notify.netty.enums.TxTransactionAction;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
public class NettyMessageServiceImpl implements TransactionManagerMessageService {


    @Override
    public boolean createTxTransactionGroupInfo(TxTransactionGroup txTransactionGroup) {

        TxTransactionMessage txTransactionMessage=new TxTransactionMessage();

        txTransactionMessage.setAction(TxTransactionAction.CREATE_GROUP.getCode());

        txTransactionMessage.setMessageId(UUID.randomUUID().toString());

        txTransactionMessage.setTxTransactionGroup(txTransactionGroup);




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
