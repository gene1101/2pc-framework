package com.fansh.transaction.core.idGenerator.base;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by fansuhuang on 2017/8/29.
 */
public class IdBlock implements Serializable {

    public static final String REDIS_ID_GENERATOR_KEY="redis_tansactionId_generator_key:";

    private static final long serialVersionUID = -8292525598047211189L;

    private AtomicLong nextId;

    private AtomicLong lastId;

    public IdBlock(AtomicLong nextId, AtomicLong lastId){
        this.nextId = nextId;
        this.lastId = lastId;
    }

    public AtomicLong getNextId() {
        return nextId;
    }

    public void setNextId(AtomicLong nextId) {
        this.nextId = nextId;
    }

    public AtomicLong getLastId() {
        return lastId;
    }

    public void setLastId(AtomicLong lastId) {
        this.lastId = lastId;
    }
}
