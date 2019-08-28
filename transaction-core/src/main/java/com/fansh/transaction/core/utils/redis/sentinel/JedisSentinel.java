package com.fansh.transaction.core.utils.redis.sentinel;

import com.fansh.transaction.core.utils.SpringBeanUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

/**
 * Created by fansuhuang on 2017/8/11.
 */

/**
 * redis哨兵工具类
 */
public class JedisSentinel {


    private static JedisSentinelPool getJedisSentinelPool(){
        Object bean  = SpringBeanUtils.getBean("jedisSentinelPool");
        return bean!= null ?(JedisSentinelPool) bean:null;
    }

    public static Jedis getJedis(){
        JedisSentinelPool pool = getJedisSentinelPool();
        if (pool!=null) return pool.getResource();
        else return null;
    }

    public static void returnJedis(Jedis jedis){
        jedis.close();
    }
}
