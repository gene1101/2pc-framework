package com.fansh.transaction.commnuication.notify.netty.channel;


import com.fansh.transaction.commnuication.exception.RemoteException;
import com.fansh.transaction.commnuication.notify.netty.ChannelHandler;
import com.fansh.transaction.commnuication.notify.netty.transport.AbstractChannel;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.util.concurrent.ConcurrentHashMap;

public class NettyChannel extends AbstractChannel {

    private static final Logger logger = LoggerFactory.getLogger(NettyChannel.class);

    private static ConcurrentHashMap<Channel,NettyChannel> channelMap=new ConcurrentHashMap<Channel, NettyChannel>();

    private final Channel channel;

    public NettyChannel(Channel channel,ChannelHandler channelHandler) {
        super(channelHandler);
        this.channel=channel;
    }

    public static NettyChannel getNettyChannel(Channel channel,ChannelHandler channelHandler){
        if (channel==null){
            return null;
        }

        NettyChannel ret=channelMap.get(channel);

        if (ret==null){
            NettyChannel nettyChannel=new NettyChannel(channel,channelHandler);
            if (channel.isActive()){
                ret=channelMap.putIfAbsent(channel,nettyChannel);
            }

            if (ret==null){
                ret=nettyChannel;
            }
        }
        return ret;

    }

    public static void removeNettyChannel(Channel channel){
        if (channel!=null && !channel.isActive()){
            channelMap.remove(channel);
        }
    }

    @Override
    public InetSocketAddress getRemoteAddress() {
        return (InetSocketAddress) channel.remoteAddress();
    }

    @Override
    public boolean isConnected() {
        return channel.isActive();
    }

    @Override
    public InetSocketAddress getLocalAddress() {
        return (InetSocketAddress) channel.localAddress();
    }

    public void send(Object message) throws RemoteException {
        super.send(message);

        boolean success = true;
        int timeout = 0;
        try {
            ChannelFuture future = channel.writeAndFlush(message);
            Throwable cause = future.cause();
            if (cause != null) {
                throw cause;
            }
        } catch (Throwable e) {
            throw new RemoteException(this, "Failed to send message " + message + " to " + getRemoteAddress() + ", cause: " + e.getMessage(), e);
        }

        if (!success) {
            throw new RemoteException(this, "Failed to send message " + message + " to " + getRemoteAddress()
                    + "in timeout(" + timeout + "ms) limit");
        }
    }

}
