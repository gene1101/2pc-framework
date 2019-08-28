package com.fansh.transaction.common.enums;

public enum PropagationEnum {

    REQUIRED(0),
    SUPPORTS(1),
    MANDATORY(2),
    REQUIRES_NEW(3),
    NOT_SUPPORTED(4),
    NEVER(5),
    NESTED(6);

    private final int value;

    private PropagationEnum(int value) {
        this.value = value;
    }

    public int value() {
        return this.value;
    }
}
