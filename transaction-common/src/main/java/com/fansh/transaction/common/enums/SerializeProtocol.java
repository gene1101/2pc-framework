package com.fansh.transaction.common.enums;

public enum SerializeProtocol {

    KYRO("kyro");


    SerializeProtocol(String protocol){
        this.protocol=protocol;
    }

    private String protocol;

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }
}
