package com.dataprocess.databases.bpm.login_info;

import com.dataprocess.core.handler.CoreHandler;
import com.dataprocess.es.entity.LoginInfoEntity;
import com.dataprocess.redis.RedisUtil;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class LoginInfoRedis extends CoreHandler {

    @Resource
    RedisUtil redisUtil;
    @Override
    public Object insert(Object obj) {
        List<Map<Object,Object>> list=(List<Map<Object,Object>>)obj;
        if(null!=list&&list.size()>0){

            for (Map<Object,Object> item: list  ) {
                redisUtil.hashPutAll("logininfo:"+item.get("id"),item);
            }

            return true;
        }
        return false;
    }

    @Override
    public Object update(Object obj) {
        redisUtil.set("1","test1");
        System.out.println("正在执行redis修改");
        return null;
    }

    @Override
    public Object delete(Object obj) {
        return null;
    }
}
