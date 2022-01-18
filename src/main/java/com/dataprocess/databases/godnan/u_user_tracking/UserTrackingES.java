package com.dataprocess.databases.godnan.u_user_tracking;

import com.dataprocess.core.template.CoreTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class UserTrackingES extends CoreTemplate {

    private final static Logger logger = LoggerFactory.getLogger(UserTrackingES.class);



    @Override
    public Object insert(Object obj) {
        logger.info("用户追踪插入es 别急作者正在开发中 下个版本加上...");
        getNext().insert(obj);

        return false;
    }

    @Override
    public Object update(Object obj) {
        logger.info("用户追踪修改es 别急作者正在开发中 下个版本加上...");
        return true;
    }

    @Override
    public Object delete(Object obj) {

        logger.info("用户追踪删除 别急作者正在开发中 下个版本加上...");
        return false;
    }


}
