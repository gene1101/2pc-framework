package com.fansh.transaction.common.seriallizer.intf;


import com.fansh.transaction.common.exception.TxTransactionException;

/**
 * 对象序列化
 */
public interface ObjectSeriallizer {

    byte[] serializer(Object object) throws TxTransactionException;

    <T> T deSerialize(byte[] data,Class<T> tClass) throws TxTransactionException;

    String getScheme();
}
