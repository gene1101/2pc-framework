package com.fansh.transaction.common.enums;

public enum TxTransactionStatus {

    /**
     * Rollback transaction status
     */
    ROLLBACK(0, "回滚"),

    /**
     * Commit transaction status
     */
    COMMIT(1, "已经提交"),


    /**
     * Begin transaction status
     */
    BEGIN(2, "开始"),

    /**
     * Running transaction status
     */
    RUNNING(3, "执行中"),

    /**
     * Failure transaction status
     */
    FAILURE(4, "失败"),

    /**
     * Complete transaction status
     */
    PRE_COMMIT(5, "预提交"),

    /**
     * Lock transaction status
     */
    LOCK(6, "锁定");


    private String message;

    private int code;

    TxTransactionStatus(int code, String message){
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
