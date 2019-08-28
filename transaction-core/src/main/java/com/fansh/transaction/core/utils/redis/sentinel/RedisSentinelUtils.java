package com.fansh.transaction.core.utils.redis.sentinel;

import redis.clients.jedis.Jedis;

/**
 * Created by fansuhuang on 2017/8/11.
 * redis高可用工具类
 */
public class RedisSentinelUtils {

    public static boolean setIN(String key,String value){
        Jedis jedis = JedisSentinel.getJedis();
        Long  result = jedis.setnx(key,value);
        JedisSentinel.returnJedis(jedis);
        return result ==1?true:false;
    }

    public static boolean set(String key ,String value){
        Jedis jedis = JedisSentinel.getJedis();
        String  result = jedis.set(key,value);
        JedisSentinel.returnJedis(jedis);
        return result.equals("1")?true:false;
    }

    public static String get(String key){
        Jedis jedis = JedisSentinel.getJedis();
        String result = jedis.get(key);
        JedisSentinel.returnJedis(jedis);
        return result;
    }

    public static String getSet(String key,String value){
        Jedis jedis = JedisSentinel.getJedis();
        String result = jedis.getSet(key,value);
        JedisSentinel.returnJedis(jedis);
        return result;
    }

    public static boolean delete(String key){
        Jedis jedis = JedisSentinel.getJedis();
        Long result = jedis.del(key);
        JedisSentinel.returnJedis(jedis);
        return result==1?true:false;
    }

    public static boolean expire(String key,int time){
        Jedis jedis = JedisSentinel.getJedis();
        Long result =  jedis.expire(key,time);
        JedisSentinel.returnJedis(jedis);
        return result==1?true:false;
    }
}
