package com.dataprocess;

import com.dataprocess.redis.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class DataProcessApplicationTest {

    @Resource
    RedisUtil redisUtil;
    @Test
    void contextLoads() {
        //表名 + id 名
        String key="dict_config:merchant_type"+"50";
        Map<Object,Object> map=new HashMap<>();
        map.put("config_type","merchant_type");
        map.put("key","1");
        map.put("value","直营客户");
        map.put("rank","1");
        redisUtil.hashPutAll(key,map);



        List<Map<Object,Object>> result= redisUtil.hashGetValue(key);
        System.out.println(result);

    }

}
