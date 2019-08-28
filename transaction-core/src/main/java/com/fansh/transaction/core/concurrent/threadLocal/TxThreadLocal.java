package com.fansh.transaction.core.concurrent.threadLocal;

public class TxThreadLocal {


    private static final TxThreadLocal  txThreadLocol=new  TxThreadLocal();

    private static final ThreadLocal<String> threadLocal=new ThreadLocal<String>();


    private TxThreadLocal(){

    }

    public static TxThreadLocal getInstance(){
        return txThreadLocol;
    }


    public void setTxGroupId(String groupId){
        threadLocal.set(groupId);
    }

    public String getTxGroupId(){
        return threadLocal.get();
    }

    public void removeTxGroupId(){
        threadLocal.remove();
    }
}
