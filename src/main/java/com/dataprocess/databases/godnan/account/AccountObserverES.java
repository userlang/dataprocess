package com.dataprocess.databases.godnan.account;

import com.dataprocess.core.template.ObserverTemplate;
import org.springframework.stereotype.Component;

@Component
public class AccountObserverES extends ObserverTemplate {
    @Override
    public Object insertData(Object obj) {

        return null;
    }

    @Override
    public Object updateData(Object obj) {
        System.out.println(" account  观察者方式正在修改es");

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
        System.out.println("观察者更新方法异常");
    }

    @Override
    public void deleteDataRollBack(Object obj) {

    }
}
