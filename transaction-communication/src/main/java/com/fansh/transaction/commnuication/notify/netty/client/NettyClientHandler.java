package com.fansh.transaction.commnuication.notify.netty.client;

import com.fansh.transaction.commnuication.notify.netty.ChannelHandler;
import com.fansh.transaction.commnuication.notify.netty.channel.NettyChannel;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;


@io.netty.channel.ChannelHandler.Sharable
public class NettyClientHandler extends ChannelDuplexHandler {

    private final ChannelHandler channelHandler;

    public NettyClientHandler(ChannelHandler channelHandler){

        if (channelHandler == null) {
            throw new IllegalArgumentException("handler == null");
        }
        this.channelHandler=channelHandler;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.fireChannelActive();
    }




    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        super.write(ctx,msg, promise);

        NettyChannel nettyChannel=NettyChannel.getNettyChannel(ctx.channel(),channelHandler);

        try{
            channelHandler.send(nettyChannel,msg);
        }finally {
            NettyChannel.removeNettyChannel(ctx.channel());
        }
    }


}
