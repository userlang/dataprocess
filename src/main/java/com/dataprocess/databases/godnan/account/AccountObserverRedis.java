package com.dataprocess.databases.godnan.account;

import com.dataprocess.core.template.ObserverTemplate;
import org.springframework.stereotype.Component;

@Component
public class AccountObserverRedis extends ObserverTemplate {
    @Override
    public Object insert(Object obj) {

        return null;
    }

    @Override
    public Object update(Object obj) {
        System.out.println(" account  观察者方式正在修改redis");
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
