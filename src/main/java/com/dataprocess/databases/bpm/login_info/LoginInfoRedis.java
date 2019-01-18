package com.dataprocess.databases.bpm.login_info;

import com.dataprocess.core.template.CoreTemplate;
import com.dataprocess.redis.RedisUtil;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Component
public class LoginInfoRedis extends CoreTemplate {

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



        System.out.println("正在执行redis修改");
        int i=20/0;
        return null;
    }

    @Override
    public Object delete(Object obj) {
        return null;
    }

    @Override
    public void insertRollBack(Object obj) {
        System.out.println("执行异常回滚");
    }

    @Override
    public void updateRollBack(Object obj) {
        System.out.println("执行updateRollBack异常回滚");
    }

    @Override
    public void deleteRollBack(Object obj) {

    }

    public RedisUtil getRedisUtil() {
        return redisUtil;
    }

    public void setRedisUtil(RedisUtil redisUtil) {
        this.redisUtil = redisUtil;
    }
}
