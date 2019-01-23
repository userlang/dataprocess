package com.dataprocess.databases.godnan.account;

import com.dataprocess.core.template.ObserverTemplate;
import org.springframework.stereotype.Component;

@Component
public class AccountObserverES extends ObserverTemplate {
    @Override
    public Object insert(Object obj) {

        return null;
    }

    @Override
    public Object update(Object obj) {
        System.out.println(" account  观察者方式正在修改es");
        int a=10/0;
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
        System.out.println("观察者更新方法异常");
    }

    @Override
    public void deleteRollBack(Object obj) {

    }
}
