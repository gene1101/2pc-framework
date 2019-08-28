package com.fansh.transaction.common.bean;

import com.fansh.transaction.common.enums.PropagationEnum;

import java.io.Serializable;


/**
 * 事物信息
 */

public class TransactionInfo implements Serializable {
    private static final long serialVersionUID = -6481089471161168988L;


    public TransactionInfo(TransactionInvocation invocation,String transactionGroupId
            ,String transactionId,int waitMaxTime,PropagationEnum propagationEnum){
        this.invocation=invocation;
        this.transactionGroupId=transactionGroupId;
        this.transactionId=transactionId;
        this.waitMaxTime=waitMaxTime;
        this.propagationEnum=propagationEnum;
    }

    private TransactionInvocation invocation;

    /**
     * 分布式事物组id
     */
    private String transactionGroupId;

    /**
     * 事物id
     */
    private String transactionId;


    /**
     * 事物最大等待时间
     */
    private int waitMaxTime =60;

    /**
     * 事物传播
     */
    private PropagationEnum propagationEnum;

    public TransactionInvocation getInvocation() {
        return invocation;
    }

    public String getTransactionGroupId() {
        return transactionGroupId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public int getWaitMaxTime() {
        return waitMaxTime;
    }

    public PropagationEnum getPropagationEnum() {
        return propagationEnum;
    }
}
