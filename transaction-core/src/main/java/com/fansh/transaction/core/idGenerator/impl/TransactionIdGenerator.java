package com.fansh.transaction.core.idGenerator.impl;

import com.fansh.transaction.common.constants.TxConstants;
import com.fansh.transaction.core.idGenerator.base.IdBlock;
import com.fansh.transaction.core.idGenerator.base.RedisIdGenerator;
import com.fansh.transaction.core.idGenerator.intf.IdGenerator;
import com.fansh.transaction.core.utils.lock.SpringDataRedisLock;
import com.fansh.transaction.core.utils.redis.springData.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by fansuhuang on 2017/8/29.
 */
public class TransactionIdGenerator implements IdGenerator {

    private static final Logger logger = LoggerFactory.getLogger(TransactionIdGenerator.class);

    /**
     * 默认初始化发号器预读步长为2500
     */
    private int idStep=2500;

    private RedisIdGenerator redisIdGenerator;


    public TransactionIdGenerator(int idStep, double threshold) throws Exception {
        if (redisIdGenerator ==null){
            IdBlock initTounaIdBlock = this.getInitIdBlock();

            if (initTounaIdBlock ==null){
                throw new Exception("init id generator failt");
            }
            redisIdGenerator = new RedisIdGenerator(initTounaIdBlock,idStep,threshold);
        }
    }


    private IdBlock getInitIdBlock(){
        SpringDataRedisLock springDataRedisLock = new SpringDataRedisLock(TxConstants.TRANSACTION_GROUP
                ,20*60*1000,20*100);
        try {
            boolean lockResult =springDataRedisLock.tryLock();

            if (!lockResult){
                logger.error("init id generator failt");
                return null;
            }


            Long redisValue  =(Long) RedisUtil.get(IdBlock.REDIS_ID_GENERATOR_KEY);

            if (redisValue == null) {

                redisValue = 0L;
                RedisUtil.set(IdBlock.REDIS_ID_GENERATOR_KEY,redisValue+idStep);
            }

            return new IdBlock(new AtomicLong(redisValue),new AtomicLong(redisValue+idStep));
        } catch (Exception e) {
            logger.error("init id generator failt");
            return null;
        }
        finally {
            springDataRedisLock.unlock();
        }
    }

    @Override
    public String getNextId(String businessKey) {
        long nextId=redisIdGenerator.getNextId();
        if (nextId <0){
            return null;
        }
        return businessKey+String.valueOf(nextId);
    }

    public int getIdStep() {
        return idStep;
    }

    public void setIdStep(int idStep) {
        this.idStep = idStep;
    }
}
