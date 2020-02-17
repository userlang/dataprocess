package com.dataprocess.databases.godnan.account;

import com.dataprocess.core.template.LinkedTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class AccountChainRedis extends LinkedTemplate {


    @Override
    public Object insertData(Object obj) {
        List<Map<String,Object>> list=(List<Map<String,Object>>)obj;

        return null;
    }

    @Override
    public Object updateData(Object obj) {

        System.out.println("account 责任链方式正在修改redis");
        return null;
    }

    @Override
    public Object deleteData(Object obj) {
        return null;
    }

    @Override
    public void insertDataRollBack(Object obj) {

    }

    @Override
    public void updateDataRollBack(Object obj) {
        System.out.println("责任链更新方法异常");
    }

    @Override
    public void deleteDataRollBack(Object obj) {

    }

}
