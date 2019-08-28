package com.fansh.transaction.commnuication.notify.netty;

public interface ChannelHandlerDelegate extends ChannelHandler {
    public ChannelHandler getHandler();
}
