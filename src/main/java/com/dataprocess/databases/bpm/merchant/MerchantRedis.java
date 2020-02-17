package com.dataprocess.databases.bpm.merchant;

import com.dataprocess.core.template.LinkedTemplate;
import org.springframework.stereotype.Component;

/**
 * @Author junnan.lang
 * @Date 2022/1/19 15:01
 * @Version 1.0
 */
@Component
public class MerchantRedis extends LinkedTemplate {

    @Override
    public Object insertData(Object obj) {
        System.out.println("正在执行商户redis添加");
        return null;
    }

    @Override
    public Object updateData(Object obj) {
        System.out.println("正在执行商户redis修改");
        return null;
    }

    @Override
    public Object deleteData(Object obj) {
        System.out.println("正在执行商户redis删除");
        return null;
    }



    @Override
    public void insertDataRollBack(Object obj) {
        System.out.println("正在执行商户添加redis回滚");
    }

    @Override
    public void updateDataRollBack(Object obj) {
        System.out.println("正在执行商户修改redis回滚");
    }

    @Override
    public void deleteDataRollBack(Object obj) {
        System.out.println("正在执行商户删除redis回滚");
    }
}
