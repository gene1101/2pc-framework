package com.fansh.transaction.commnuication.message;

import java.io.Serializable;

public class TxTransactionMessage implements Serializable {
    private static final long serialVersionUID = 1213687051021150345L;

    private int action;

    private TxTransactionGroup txTransactionGroup;

    private String messageId;

    private String result;

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public TxTransactionGroup getTxTransactionGroup() {
        return txTransactionGroup;
    }

    public void setTxTransactionGroup(TxTransactionGroup txTransactionGroup) {
        this.txTransactionGroup = txTransactionGroup;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }
}
