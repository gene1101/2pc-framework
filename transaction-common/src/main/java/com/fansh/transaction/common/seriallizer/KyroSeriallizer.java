package com.fansh.transaction.common.seriallizer;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.fansh.transaction.common.enums.SerializeProtocol;
import com.fansh.transaction.common.exception.TxTransactionException;
import com.fansh.transaction.common.seriallizer.intf.ObjectSeriallizer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class KyroSeriallizer implements ObjectSeriallizer {


    /**
     * 通过kryo序列化对象
     * @param object
     * @return
     * @throws TxTransactionException
     */
    public byte[] serializer(Object object) throws TxTransactionException {
        byte[] bytes;

        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();

        try {
            Kryo kryo=new Kryo();

            Output output=new Output(byteArrayOutputStream);
            kryo.writeObject(output,object);
            bytes=output.toBytes();
            output.flush();
        }catch (Exception e){
            throw new TxTransactionException("kyro serialize error,stack e:"+e.getCause());
        }
        finally {
            try{
                byteArrayOutputStream.flush();
                byteArrayOutputStream.close();
            }
            catch (IOException e){

            }
        }
        return bytes;
    }

    public <T> T deSerialize(byte[] data, Class<T> tClass) throws TxTransactionException {
        T object;

        try{
            ByteArrayInputStream inputStream=new ByteArrayInputStream(data);
            Kryo kryo=new Kryo();

            Input input=new Input(inputStream);
            object=kryo.readObject(input,tClass);
            input.close();
        }catch (Exception e){
            throw new TxTransactionException("kryo deSerialize error,stack message:"+e.getCause());
        }

        return object;
    }

    public String getScheme() {
        return SerializeProtocol.KYRO.getProtocol();
    }
}
