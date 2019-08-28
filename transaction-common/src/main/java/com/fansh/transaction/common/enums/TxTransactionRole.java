package com.fansh.transaction.common.enums;

public enum  TxTransactionRole {

    STARTER(0,"发起者"),

    SLAVER(1,"从事物参与者");


    private String message;

    private int code;

    TxTransactionRole(int code, String message){
        this.message=message;
        this.code=code;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
