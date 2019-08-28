package com.fansh.transaction.commnuication.notify.netty.client;

import com.fansh.transaction.commnuication.notify.netty.ChannelHandler;
import com.fansh.transaction.commnuication.notify.netty.transport.AbstractPeer;
import com.fansh.transaction.commnuication.utils.ExecutorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;

public abstract class AbstractClient extends AbstractPeer {

    private static final Logger logger= LoggerFactory.getLogger(AbstractClient.class);

    protected volatile ExecutorService executor;

    public AbstractClient(ChannelHandler channelHandler){
        super(channelHandler);

        try{
            open();
        }
        catch (Throwable throwable){

        }
    }


    @Override
    public InetSocketAddress getLocalAddress() {
        return null;
    }


    protected abstract void open() throws Throwable;


    public void close(){

        if (executor!=null){
            ExecutorUtil.shutdownNow(executor,100);
        }

        try {
            super.close();
        } catch (Throwable e) {
            logger.warn(e.getMessage(), e);
        }

        try{
            disconnect();
        }
        catch (Throwable throwable){

        }

    };

    public void disconnect(){

    }
}
