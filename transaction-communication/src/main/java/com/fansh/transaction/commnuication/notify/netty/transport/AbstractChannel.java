package com.fansh.transaction.commnuication.notify.netty.transport;

import com.fansh.transaction.commnuication.exception.RemoteException;
import com.fansh.transaction.commnuication.notify.netty.Channel;
import com.fansh.transaction.commnuication.notify.netty.ChannelHandler;
import com.fansh.transaction.commnuication.notify.netty.EndPoint;


public abstract class AbstractChannel implements EndPoint,ChannelHandler,Channel {

    private final ChannelHandler channelHandler;

    // closing closed分别表示关闭流程中、完成关闭
    private volatile boolean closing;

    private volatile boolean closed;

    public AbstractChannel(ChannelHandler channelHandler){
        if (channelHandler == null) {
            throw new IllegalArgumentException("handler == null");
        }
        this.channelHandler=channelHandler;
    }

    @Override
    public void connected(Channel channel) throws RemoteException {
        if (closed) return;
        channelHandler.connected(channel);
    }

    @Override
    public void disconnected(Channel channel) throws RemoteException {
        channelHandler.disconnected(channel);
    }

    @Override
    public void send(Channel channel, Object message) throws RemoteException {
        if (closed) return;
        channelHandler.send(channel,message);
    }

    @Override
    public void received(Channel channel, Object message) throws RemoteException {
        if (closed) return;
        channelHandler.received(channel,message);
    }

    @Override
    public void caught(Channel channel,Throwable ex) throws RemoteException {
        channelHandler.caught(channel,ex);
    }


    @Override
    public ChannelHandler getChannelHandler() {
        return channelHandler;
    }

    @Override
    public void send(Object message) throws RemoteException {
       send(message);
    }

    @Override
    public void asynSend(Object message) throws RemoteException {

    }
}
