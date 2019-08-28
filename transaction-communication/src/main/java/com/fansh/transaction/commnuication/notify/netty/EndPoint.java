package com.fansh.transaction.commnuication.notify.netty;

import com.fansh.transaction.commnuication.exception.RemoteException;

import java.net.InetSocketAddress;

public interface EndPoint {

    InetSocketAddress getLocalAddress();

    ChannelHandler getChannelHandler();

    void send(Object message) throws RemoteException;

    void asynSend(Object message) throws RemoteException;


}
