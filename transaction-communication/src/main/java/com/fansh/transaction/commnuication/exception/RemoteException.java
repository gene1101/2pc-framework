package com.fansh.transaction.commnuication.exception;


import com.fansh.transaction.commnuication.notify.netty.Channel;

import java.net.InetSocketAddress;

public class RemoteException extends Exception {
    private static final long serialVersionUID = -8997427992109996488L;

    private InetSocketAddress localAddress;

    private InetSocketAddress remoteAddress;


    public RemoteException(InetSocketAddress localAddress,InetSocketAddress remoteAddress,String msg){
        super(msg);
        this.localAddress=localAddress;
        this.remoteAddress=remoteAddress;
    }


    public  RemoteException(Channel channel, String msg){
        this(channel==null?null:channel.getLocalAddress(),channel==null?null:channel.getRemoteAddress(),msg);
    }


    public RemoteException(InetSocketAddress localAddress,InetSocketAddress remoteAddress,Throwable throwable){
        super(throwable);
        this.localAddress=localAddress;
        this.remoteAddress=remoteAddress;
    }


    public  RemoteException(Channel channel,String message, Throwable throwable){
        this(channel==null?null:channel.getLocalAddress(),channel==null?null:channel.getRemoteAddress(),message,throwable);
    }


    public RemoteException(InetSocketAddress localAddress,InetSocketAddress remoteAddress,String message,Throwable throwable){
        super(message,throwable);
        this.localAddress=localAddress;
        this.remoteAddress=remoteAddress;
    }


    public  RemoteException(Channel channel, Throwable throwable){
        this(channel==null?null:channel.getLocalAddress(),channel==null?null:channel.getRemoteAddress(),throwable);
    }

    public InetSocketAddress getLocalAddress() {
        return localAddress;
    }

    public InetSocketAddress getRemoteAddress() {
        return remoteAddress;
    }
}
