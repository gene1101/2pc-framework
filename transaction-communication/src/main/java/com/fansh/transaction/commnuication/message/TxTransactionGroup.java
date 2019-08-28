package com.fansh.transaction.commnuication.message;

import java.io.Serializable;
import java.util.List;

public class TxTransactionGroup implements Serializable {

    private static final long serialVersionUID = 4366278677511827132L;

    /**
     * 分布式事物组id
     */
    private String id;


    /**
     * 分布式事物组的最大等待时间
     */
    private int waitMaxTime;

    /**
     * 分布式事物组状态
     */
    private int status;

    /**
     * 组内各个事物信息
     */


    private List<SingleTransaction> singleTransactionList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getWaitMaxTime() {
        return waitMaxTime;
    }

    public void setWaitMaxTime(int waitMaxTime) {
        this.waitMaxTime = waitMaxTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<SingleTransaction> getSingleTransactionList() {
        return singleTransactionList;
    }

    public void setSingleTransactionList(List<SingleTransaction> singleTransactionList) {
        this.singleTransactionList = singleTransactionList;
    }
}
