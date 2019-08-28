package com.fansh.transaction.common.exception;


import java.io.Serializable;

public class TxTransactionException extends Exception{


    private static final long serialVersionUID = -5141218250303043409L;

    public TxTransactionException(){

    }

    public TxTransactionException(String message){
        super(message);
    }

    public TxTransactionException(String message, Throwable cause) {
        super(message, cause);
    }

    public TxTransactionException(Throwable cause) {
        super(cause);
    }
}
