package com.dataprocess.databases.bpm.login_info;

import com.dataprocess.core.template.LinkedTemplate;
import com.dataprocess.redis.RedisUtil;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class LoginInfoRedis extends LinkedTemplate {

    @Resource
    RedisUtil redisUtil;



    @Override
    public Object insertData(Object obj) {
        System.out.println("正在执行 LoginInfoRedis添加");
        return true;
    }

    @Override
    public Object updateData(Object obj) {
        System.out.println("正在执行 LoginInfoRedis修改");
        return true;
    }

    @Override
    public Object deleteData(Object obj) {
        System.out.println("正在执行 LoginInfoRedis删除");
        return true;
    }

    @Override
    public void insertDataRollBack(Object obj) {
        System.out.println("正在执行 LoginInfoRedis 添加异常");
    }

    @Override
    public void updateDataRollBack(Object obj) {
        System.out.println("正在执行 LoginInfoRedis 修改异常");
    }

    @Override
    public void deleteDataRollBack(Object obj) {
        System.out.println("正在执行 LoginInfoRedis 删除异常");
    }

    public RedisUtil getRedisUtil() {
        return redisUtil;
    }

    public void setRedisUtil(RedisUtil redisUtil) {
        this.redisUtil = redisUtil;
    }
}
