package com.fansh.transaction.core.idGenerator.base;


import com.fansh.transaction.core.utils.redis.springData.RedisUtil;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by fansuhuang on 2017/8/29.
 */
public class RedisIdGenerator implements Serializable {

    private static final long serialVersionUID = 1017127909245639575L;

    private LinkedList<IdBlock> linkedList = new LinkedList<IdBlock>();

    private IdBlock firstTounaBlock;

    private IdBlock LastTounaBlock =new IdBlock( new AtomicLong(0),new AtomicLong(0));

    private int idStep = 2500;

    private double threshold =0.80f;

    public RedisIdGenerator(IdBlock initTounaBlock, Integer idStep, Double threshold){
        this.firstTounaBlock = initTounaBlock;
        if (idStep !=null && idStep>0){
            this.idStep =idStep;
        }
        if (threshold != null && threshold>0.0f) {
            this.threshold = threshold;
        }
        initDoubleBuffer();
    }

    private void initDoubleBuffer(){
        if (firstTounaBlock ==null){
            firstTounaBlock =new IdBlock(new AtomicLong(0),new AtomicLong(idStep));
            RedisUtil.incrBy(IdBlock.REDIS_ID_GENERATOR_KEY,idStep);
        }

        linkedList.addFirst(firstTounaBlock);
        linkedList.addLast(LastTounaBlock);
    }

    public synchronized long getNextId(){
        long result =-1;
        if (firstTounaBlock.getNextId().get()<firstTounaBlock.getLastId().get()){
            result = firstTounaBlock.getNextId().incrementAndGet();
            if (firstTounaBlock.getLastId().intValue()*threshold<result){
                if (LastTounaBlock.getLastId().get()<firstTounaBlock.getLastId().get()){
                    Long redisValue = RedisUtil.incrBy(IdBlock.REDIS_ID_GENERATOR_KEY,idStep);
                    if (redisValue !=null){
                        LastTounaBlock.getLastId().set(redisValue.intValue());
                        LastTounaBlock.getNextId().set(redisValue.intValue()-idStep);
                    }
                }
            }
            return result;
        }
        else {
            /**
             * 切换buffer
             */
            IdBlock first = linkedList.getFirst();
            IdBlock last = linkedList.getLast();
            linkedList.removeFirst();
            linkedList.removeLast();
            linkedList.addFirst(last);
            linkedList.addLast(first);
            return getNextId();
        }
    }
}
