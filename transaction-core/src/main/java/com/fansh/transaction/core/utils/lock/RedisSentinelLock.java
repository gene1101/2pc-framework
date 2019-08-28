package com.fansh.transaction.core.utils.lock;

import com.fansh.transaction.core.utils.redis.sentinel.RedisSentinelUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by fansuhuang on 2017/8/9.
 */
public class RedisSentinelLock {

    private static final Logger logger = LoggerFactory.getLogger(RedisSentinelLock.class);

    /**
     * 锁的过期时间
     */
    private int expireTime = 60*1000;

    private int threadWaitTime =10 * 100;

    private String lockKey;

    private int aquire_millis = 100;

    private volatile boolean isLock =false;

    public RedisSentinelLock(String lockKey, int expireTime, int threadWaitTime) {
        this.lockKey = lockKey;
        this.expireTime = expireTime;
        this.threadWaitTime = threadWaitTime;
    }

    public synchronized  boolean tryLock() throws InterruptedException{
        int waitTime = threadWaitTime;

        while (waitTime>=0){
            String expireTimeStr = String.valueOf(System.currentTimeMillis() + expireTime + 1);
            if (RedisSentinelUtils.setIN(lockKey,expireTimeStr)){
                isLock = true;
                return true;
            }

            String currentKeyValue =(String) RedisSentinelUtils.get(lockKey);

            /**
             * 如果锁已经过期
             */
            if (currentKeyValue != null && Long.parseLong(currentKeyValue)<System.currentTimeMillis()){

                expireTimeStr =  String.valueOf(System.currentTimeMillis() + expireTime + 1);

                String oldKeyValue = RedisSentinelUtils.getSet(lockKey,expireTimeStr);

                if (Long.parseLong(oldKeyValue)<= System.currentTimeMillis()){
                    isLock = true;
                    return  true;
                }
            }
            waitTime -=aquire_millis;
            Thread.sleep(aquire_millis);
        }
        isLock = false;
        return false;
    }

    public synchronized void unlock(){
       if (isLock){
           RedisSentinelUtils.delete(lockKey);
       }
    }

}
