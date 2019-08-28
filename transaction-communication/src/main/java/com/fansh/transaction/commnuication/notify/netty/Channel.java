package com.fansh.transaction.commnuication.notify.netty;


import java.net.InetSocketAddress;

public interface Channel extends EndPoint {

    InetSocketAddress getRemoteAddress();

    /**
     * is connected.
     *
     * @return connected
     */
    boolean isConnected();
}
