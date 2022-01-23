package com.dataprocess.databases.bpm.login_info;

import com.dataprocess.core.template.ChainTemplate;
import com.dataprocess.redis.RedisUtil;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class LoginInfoRedis extends ChainTemplate {

    @Resource
    RedisUtil redisUtil;



    @Override
    public Object insert(Object obj) {
        System.out.println("正在执行 LoginInfoRedis添加");
        return true;
    }

    @Override
    public Object update(Object obj) {
        System.out.println("正在执行 LoginInfoRedis修改");
        return true;
    }

    @Override
    public Object delete(Object obj) {
        System.out.println("正在执行 LoginInfoRedis删除");
        return true;
    }

    @Override
    public void insertRollBack(Object obj) {
        System.out.println("正在执行 LoginInfoRedis 添加异常");
    }

    @Override
    public void updateRollBack(Object obj) {
        System.out.println("正在执行 LoginInfoRedis 修改异常");
    }

    @Override
    public void deleteRollBack(Object obj) {
        System.out.println("正在执行 LoginInfoRedis 删除异常");
    }

    public RedisUtil getRedisUtil() {
        return redisUtil;
    }

    public void setRedisUtil(RedisUtil redisUtil) {
        this.redisUtil = redisUtil;
    }
}
