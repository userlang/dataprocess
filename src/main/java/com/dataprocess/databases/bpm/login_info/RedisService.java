package com.dataprocess.databases.bpm.login_info;

import com.dataprocess.core.handler.CoreHandler;
import com.dataprocess.redis.RedisUtil;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class RedisService extends CoreHandler {

    @Resource
    RedisUtil redisUtil;
    @Override
    public Object insert(Object obj) {
        redisUtil.set("1","test");
        return null;
    }

    @Override
    public Object update(Object obj) {
        redisUtil.set("1","test1");
        return null;
    }

    @Override
    public Object delete(Object obj) {
        return null;
    }
}
