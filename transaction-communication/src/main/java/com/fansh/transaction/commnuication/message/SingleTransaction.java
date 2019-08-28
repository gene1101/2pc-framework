package com.fansh.transaction.commnuication.message;

import java.io.Serializable;
import java.util.Date;

public class SingleTransaction  implements Serializable{

    private static final long serialVersionUID = 2817152558613801887L;

    /**
     * 事物组id
     */
    private String txGroupId;

    /**
     * 事物id
     */
    private String txId;

    /**
     * 事物状态
     */
    private int status;

    /**
     * 最大等待时间
     */
    private int waitMaxTime;

    /**
     * 事物参与的角色
     */
    private int role;

    /**
     * 参与事物的应用ip
     */
    private String ip;

    /**
     * 参与事物的模块
     */
    private String moduleName;


    /**
     * 事物的创建时间
     */
    private String createDate;

    /**
     * 执行类名称
     */
    private String targetClass;

    /**
     * 执行方法
     */
    private String targetMethod;

    /**
     * 耗时 秒
     */
    private Long costTime;


    /**
     * 操作结果信息
     */
    private Object result;

    public String getTxGroupId() {
        return txGroupId;
    }

    public void setTxGroupId(String txGroupId) {
        this.txGroupId = txGroupId;
    }

    public String getTxId() {
        return txId;
    }

    public void setTxId(String txId) {
        this.txId = txId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getWaitMaxTime() {
        return waitMaxTime;
    }

    public void setWaitMaxTime(int waitMaxTime) {
        this.waitMaxTime = waitMaxTime;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getTargetClass() {
        return targetClass;
    }

    public void setTargetClass(String targetClass) {
        this.targetClass = targetClass;
    }

    public String getTargetMethod() {
        return targetMethod;
    }

    public void setTargetMethod(String targetMethod) {
        this.targetMethod = targetMethod;
    }

    public Long getCostTime() {
        return costTime;
    }

    public void setCostTime(Long costTime) {
        this.costTime = costTime;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
