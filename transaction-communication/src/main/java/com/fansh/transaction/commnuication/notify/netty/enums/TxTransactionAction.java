package com.fansh.transaction.commnuication.notify.netty.enums;

public enum TxTransactionAction {

    CREATE_GROUP(0,"创建事物组"),

    JOIN_GROUP(1,"加入事物组"),

    UPDATE_TXTRANSACTION_STAUS(2,"更新事物组状态"),

    PRE_COMMIT(3,"事物预提交"),

    COMPLETE_COMMIT(4,"完成事物提交"),

    ROLL_BACK(5,"事物回滚"),

    FAILT(6,"失败"),

    KEEP_HEART(7,"保持心跳"),

    SEND(8,"发送"),

    RECEIVE(9,"接受"),

    GET_TRANSACTION_GROUP_STATUS(10,"获取事务组状态"),



    FIND_TRANSACTION_GROUP_INFO(11,"获取事务组信息");


    TxTransactionAction(int code,String message){
        this.code=code;
        this.message=message;
    }

    private int code;

    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
