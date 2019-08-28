package com.fansh.transaction.common.config;

import java.io.Serializable;

public class TxZookeeperConfig implements Serializable {

    private static final long serialVersionUID = 2693502126415826674L;

    private String host;

    private int port;

    private int sessionTimeOut = 1000;

    private String root="/txTransaction";

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getSessionTimeOut() {
        return sessionTimeOut;
    }

    public void setSessionTimeOut(int sessionTimeOut) {
        this.sessionTimeOut = sessionTimeOut;
    }

    public String getRoot() {
        return root;
    }

    public void setRoot(String root) {
        this.root = root;
    }
}
