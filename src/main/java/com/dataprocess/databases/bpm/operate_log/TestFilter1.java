package com.dataprocess.databases.bpm.operate_log;

import com.dataprocess.core.template.CoreTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class TestFilter1 extends CoreTemplate {
    @Override
    public Object insert(Object obj) {
        System.out.println("正在执行 operate_log表的 test1 添加操作 开始");
        Map<String,Object> test=new HashMap<>();
        test.put("reqParam",obj);
        test.put("test1","我是test1");
         getNext().insert(test);
        System.out.println("正在执行 operate_log表的 test1  添加操作 结束");
        return null;
    }

    @Override
    public Object update(Object obj) {
        return null;
    }

    @Override
    public Object delete(Object obj) {
        return null;
    }

    @Override
    public void insertRollBack(Object obj) {

    }

    @Override
    public void updateRollBack(Object obj) {

    }

    @Override
    public void deleteRollBack(Object obj) {

    }
}
