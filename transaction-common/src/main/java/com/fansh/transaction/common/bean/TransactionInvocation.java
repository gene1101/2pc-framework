package com.fansh.transaction.common.bean;

import java.io.Serializable;

/**
 * 事物执行器
 */
public class TransactionInvocation implements Serializable {

    private static final long serialVersionUID = -2288200556467671976L;

    public TransactionInvocation(){

    }

    public TransactionInvocation(String method,Object[] argumentValues,Class[] argumentTypes,Class targetClass){
       this.argumentTypes=argumentTypes;
       this.argumentValues=argumentValues;
       this.method=method;
       this.targetClass=targetClass;
    }

    private String method;

    private Object[] argumentValues;

    private Class[] argumentTypes;

    private Class targetClass;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Object[] getArgumentValues() {
        return argumentValues;
    }

    public void setArgumentValues(Object[] argumentValues) {
        this.argumentValues = argumentValues;
    }

    public Class[] getArgumentTypes() {
        return argumentTypes;
    }

    public void setArgumentTypes(Class[] argumentTypes) {
        this.argumentTypes = argumentTypes;
    }

    public Class getTargetClass() {
        return targetClass;
    }

    public void setTargetClass(Class targetClass) {
        this.targetClass = targetClass;
    }
}
