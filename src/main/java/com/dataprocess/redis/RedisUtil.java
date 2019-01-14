package com.dataprocess.redis;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;



@Component
public class RedisUtil {

	protected RedisTemplate<String, Object> redisTemplate;

	@Resource
	public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	/**
	 * 写入缓存
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean set(final String key, Object value, long timeOut) {
		boolean result = false;
		try {
			ValueOperations<String, Object> operations = redisTemplate.opsForValue();
			operations.set(key, value, timeOut, TimeUnit.SECONDS);

			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 写入缓存
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean set(final String key, Object value) {
		boolean result = false;
		try {
			ValueOperations<String, Object> operations = redisTemplate.opsForValue();
			operations.set(key, value);

			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public Object update(String key, Object value) {
		ValueOperations<String, Object> operations = redisTemplate.opsForValue();

		if (redisTemplate.hasKey(key)) {
			Long timeOut = redisTemplate.getExpire(key);
			operations.set(key, value, timeOut, TimeUnit.SECONDS);
		}
		return  operations.get(key);
	}

	/**
	 * 读取缓存
	 * 
	 * @param key
	 * @return
	 */
	public Object get(final String key) {
		Object result = null;
		try {
			ValueOperations<String, Object> operations = redisTemplate.opsForValue();
			if (!exists(key)) {
				return null;
			}
			result = operations.get(key);
			return result;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * 删除缓存
	 */
	public void remove(final String key) {
		redisTemplate.delete(key);

	}

	public boolean exists(String key) {

		return redisTemplate.hasKey(key);
	}

	public  Long createIncrement(String key,int value){
		ValueOperations<String, Object> operations = redisTemplate.opsForValue();
		return operations.increment(key, value);
	}


	public void expire(String key,int coloseSeconds){

		redisTemplate.expire(key,coloseSeconds,TimeUnit.SECONDS);
	}
	/*添加当天新增用户数量*/
	public void newIncrement() {
		ValueOperations<String, Object> operations = redisTemplate.opsForValue();
		operations.increment("new", 1);
	}
	/*添加当天活跃用户数量*/
	public void activeIncrement() {
		ValueOperations<String, Object> operations = redisTemplate.opsForValue();
		operations.increment("active", 1);
	}
	/*获取当天新增用户数量*/
	public Integer getNewCount() {
		return Integer.valueOf(redisTemplate.boundValueOps("new").get(0, -1));
	}
	/*获取当天活跃用户数量*/
	public Integer getActiveCount() {
		
		return Integer.valueOf(redisTemplate.boundValueOps("active").get(0, -1));

	}


	public boolean hashPut(Object key,Object hashKey,Object value){
		HashOperations hash= redisTemplate.opsForHash();
		hash.put(key,hashKey,value);
		return true;
	}
	public boolean hashPutAll(Object key, Map<Object,Object> value){
		HashOperations hash= redisTemplate.opsForHash();
		hash.putAll(key,value);
		return true;
	}
	public List<Map<Object,Object>> hashGetValue(Object key){
		HashOperations hash= redisTemplate.opsForHash();
		return  hash.values(key);
	}

}
