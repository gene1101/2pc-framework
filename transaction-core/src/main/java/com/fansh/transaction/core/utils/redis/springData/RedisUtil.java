package com.fansh.transaction.core.utils.redis.springData;

import com.fansh.transaction.core.utils.SpringBeanUtils;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class RedisUtil {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(RedisUtil.class);

    /**
     * 
     * <批量删除对应value> <功能详细描述>
     * 
     * @param keys
     */
    public static void remove(final String... keys) {
        for (String key : keys) {
            remove(key);
        }
    }
    
    private static RedisTemplate<Serializable, Object> getredisTemplate(){
        RedisTemplate<Serializable, Object> redisTemplate = (RedisTemplate<Serializable, Object>) SpringBeanUtils
                .getBeanByBeanName("redisTemplate");
        return redisTemplate;
    }

    /**
     * 
     * <批量删除key> <功能详细描述>
     * 
     * @param pattern
     */
    public static void removePattern(final String pattern) {
        RedisTemplate<Serializable, Object> redisTemplate = getredisTemplate();
        Set<Serializable> keys = redisTemplate.keys(pattern);
        if (!CollectionUtils.isEmpty(keys)) {
            redisTemplate.delete(keys);
        }
    }

    /**
     * 
     * <删除对应key> <功能详细描述>
     * 
     * @param key
     */
    public static void remove(String key) {
        if (isExists(key)) {
            RedisTemplate<Serializable, Object> redisTemplate =getredisTemplate();
            redisTemplate.delete(key);
        }
    }

    /**
     * 
     * <判断key是否存在> <功能详细描述>
     * 
     * @param key
     * @return
     */
    private static boolean isExists(String key) {
        RedisTemplate<Serializable, Object> redisTemplate = getredisTemplate();
        return redisTemplate.hasKey(key);
    }

    /**
     * 
     * <获取缓存信息> <功能详细描述>
     * 
     * @param key
     * @return
     */
    public static Object get(final String key) {
        Object result = null;
        RedisTemplate<Serializable, Object> redisTemplate = getredisTemplate();
        ValueOperations<Serializable, Object> operations = redisTemplate
                .opsForValue();
        result = operations.get(key);
        return result;
    }

    /**
     * 
     * <写入缓存> <功能详细描述>
     * 
     * @param key
     * @param obj
     * @return
     */
    public static boolean set(final String key, Object obj) {
        boolean result = false;
        try {
            RedisTemplate<Serializable, Object> redisTemplate = getredisTemplate();
            ValueOperations<Serializable, Object> operations = redisTemplate
                    .opsForValue();
            operations.set(key, obj);
            result = true;
        }
        catch (Exception e) {
            logger.error("写入缓存失败");
        }
        return result;
    }

    /**
     * 
     * <写入缓存并设置缓存失效时间> <功能详细描述>
     * 
     * @param key
     * @param obj
     * @return expireTime 秒
     */
    public static boolean set(final String key, Object obj, Long expireTime) {
        boolean result = false;
        try {
            RedisTemplate<Serializable, Object> redisTemplate = getredisTemplate();
            ValueOperations<Serializable, Object> operations = redisTemplate
                    .opsForValue();
            operations.set(key, obj, expireTime,TimeUnit.SECONDS);
            result = true;
        }
        catch (Exception e) {
            logger.error("写入缓存失败");
        }
        return result;
    }

    /**
     * 
     * <获取hashmap格式的缓存>
     * <功能详细描述>
     * @param key
     * @return
     */
    public static Object getHash(final String key) {
        Object result=null;
        RedisTemplate<Serializable, Object> redisTemplate = getredisTemplate();
        HashOperations<Serializable, Object,Object> operations=redisTemplate.opsForHash();
        Map<Object,Object> map=operations.entries(key); 
        result=map;
        if (map==null|| map.isEmpty()){
            result=null;
        }
        return result;
    }
    
    /**
     * 
     * <获取hashmap格式的缓存>
     * <功能详细描述>
     * @param key    父节点key
     * @param field  map对象key
     * @return
     */
    public static Object getHash(final String key,String field) {
        Object result=null;
        RedisTemplate<Serializable, Object> redisTemplate = getredisTemplate();
        HashOperations<Serializable, Object,Object> operations=redisTemplate.opsForHash();
        result=operations.get(key,field);
        return result;
    }    


    /**
     * 
     * <以hashmap的形式缓存数据>
     * <功能详细描述>
     * @param key
     * @param map
     * @return
     */
    public static boolean setHash(final String key,Map<? extends Object,? extends Object> map) {
        boolean result=false;
        try{
            RedisTemplate<Serializable, Object> redisTemplate = getredisTemplate();
            HashOperations<Serializable, Object,Object> operations=redisTemplate.opsForHash(); 
            operations.putAll(key, map);
            result=true;
        }
        catch(Exception e){
            logger.error("写入缓存失败");
        }
        return result;
    }


    /**
     * 
     * <以hashmap的形式缓存数据>
     * <功能详细描述>
     * @param key
     * @param field
     * @param value
     */
    public static void setHash(String key, Object field, Object value) {
        try{
            RedisTemplate<Serializable, Object> redisTemplate = getredisTemplate();
            HashOperations<Serializable, Object,Object> operations=redisTemplate.opsForHash(); 
            operations.put(key, field, value);
        }
        catch(Exception e){
            logger.error("写入缓存失败");
        }
    }    



    /**
     * setnx方法
     * @param key
     * @param value
     * @return
     */
    public static boolean setNX(final String key, final String value) throws Exception{
         RedisTemplate<Serializable, Object> redisTemplate = getredisTemplate();
         Boolean result = false;
         try{
             result = redisTemplate.execute(new RedisCallback<Boolean>(){

                 @Override
                 public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                     StringRedisSerializer serializer = new StringRedisSerializer();
                     Boolean setResult = connection.setNX(serializer.serialize(key),serializer.serialize(value));
                     connection.close();
                     return setResult;
                 }
             });
         }
         catch(Exception e){
             logger.error("setNX redis error, key : "+key,e);
             throw e;
        }

        return result;
    }


    /**
     * redis getSet方法
     * @param key
     * @param value
     * @return
     */
    public static String getSet(final String key,final String value){
        String result = null;
        RedisTemplate<Serializable, Object> redisTemplate = getredisTemplate();
        try {
            result = redisTemplate.execute(new RedisCallback<String>() {

                @Override
                public String doInRedis(RedisConnection connection) throws DataAccessException {
                    StringRedisSerializer serializer = new StringRedisSerializer();
                    byte[] oldValue = connection.getSet(serializer.serialize(key), serializer.serialize(value));

                    connection.close();
                    return serializer.deserialize(oldValue);
                }
            });
        }
         catch(Exception e){
             logger.error("getSet redis error, key : "+key,e);
        }
        return result;
    }

    /**
     * redis incrBy
     * @param key  key
     * @param value 增加步长
     * @return
     */
    public static Long incrBy(final String key,final long value){
        Long result = null;
        RedisTemplate<Serializable, Object> redisTemplate = getredisTemplate();
        try {
            result = redisTemplate.execute(new RedisCallback<Long>() {

                @Override
                public Long doInRedis(RedisConnection connection) throws DataAccessException {
                    StringRedisSerializer serializer = new StringRedisSerializer();
                    Long resultValue = connection.incrBy(serializer.serialize(key), value);
                    connection.close();
                    return resultValue;
                }
            });
        }
        catch(Exception e){
            logger.error("incrBy redis error, key : "+key,e);
        }
        return result;
    }



}
