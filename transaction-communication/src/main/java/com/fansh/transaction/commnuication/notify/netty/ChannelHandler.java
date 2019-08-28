package com.fansh.transaction.commnuication.notify.netty;


import com.fansh.transaction.commnuication.exception.RemoteException;

public interface ChannelHandler {

    void connected(Channel channel) throws RemoteException;

    void disconnected(Channel channel) throws RemoteException;

    void send(Channel channel,Object message) throws RemoteException;

    void received(Channel channel,Object message) throws RemoteException;

    void caught(Channel channel,Throwable ex) throws RemoteException;
}
