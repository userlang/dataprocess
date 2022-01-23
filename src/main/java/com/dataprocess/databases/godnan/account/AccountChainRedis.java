package com.dataprocess.databases.godnan.account;

import com.dataprocess.core.template.ChainTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class AccountChainRedis extends ChainTemplate {


    @Override
    public Object insert(Object obj) {
        List<Map<String,Object>> list=(List<Map<String,Object>>)obj;

        return null;
    }

    @Override
    public Object update(Object obj) {

        System.out.println("account 责任链方式正在修改redis");
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
