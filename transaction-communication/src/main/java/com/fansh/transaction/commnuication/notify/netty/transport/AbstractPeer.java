package com.fansh.transaction.commnuication.notify.netty.transport;

import com.fansh.transaction.commnuication.exception.RemoteException;
import com.fansh.transaction.commnuication.notify.netty.Channel;
import com.fansh.transaction.commnuication.notify.netty.ChannelHandler;
import com.fansh.transaction.commnuication.notify.netty.ChannelHandlerDelegate;
import com.fansh.transaction.commnuication.notify.netty.EndPoint;

import java.net.InetSocketAddress;

public abstract class AbstractPeer implements EndPoint,ChannelHandler {

    private final ChannelHandler handler;

    // closing closed分别表示关闭流程中、完成关闭
    private volatile boolean closing;

    private volatile boolean closed;

    public AbstractPeer(ChannelHandler channelHandler){

        if (channelHandler==null){
            throw new IllegalArgumentException("handler == null");
        }

        this.handler=channelHandler;
    }

    @Override
    public void connected(Channel channel) throws RemoteException {
        if (closed) return;
        handler.connected(channel);
    }

    @Override
    public void disconnected(Channel channel) throws RemoteException {
        handler.disconnected(channel);
    }

    @Override
    public void send(Channel channel, Object message) throws RemoteException {
        if (closed) return;
        handler.send(channel,message);
    }

    @Override
    public void received(Channel channel, Object message) throws RemoteException {
        if (closed) return;
        handler.received(channel,message);
    }

    @Override
    public void caught(Channel channel, Throwable ex) throws RemoteException {
        if (closed) return;
        handler.caught(channel,ex);
    }



    @Override
    public ChannelHandler getChannelHandler() {

        if (handler instanceof ChannelHandlerDelegate){
            return  ((ChannelHandlerDelegate) handler).getHandler();
        }
        else{
            return handler;
        }
    }

    public void close(int timeout) {
        close();
    }

    public void close() {
        closed = true;
    }

    public void startClose() {
        if (isClosed()) {
            return;
        }
        closing = true;
    }

    public boolean isClosed() {
        return closed;
    }

    public boolean isClosing() {
        return closing && !closed;
    }


    @Override
    public void send(Object message) throws RemoteException {
        send(message);
    }

    @Override
    public void asynSend(Object message) throws RemoteException {

    }
}
