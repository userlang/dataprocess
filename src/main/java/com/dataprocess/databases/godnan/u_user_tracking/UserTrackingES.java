package com.dataprocess.databases.godnan.u_user_tracking;

import com.dataprocess.core.handler.CoreHandler;
import com.dataprocess.es.dao.UserTrackingDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class UserTrackingES extends CoreHandler {

    private final static Logger logger = LoggerFactory.getLogger(UserTrackingES.class);

    @Resource
    private UserTrackingDao userTrackingDao;

    @Override
    public Object insert(Object obj) {
        logger.info("用户追踪插入es");
        successor.insert(obj);

        return false;
    }

    @Override
    public Object update(Object obj) {
        logger.info("用户追踪修改es");
        return true;
    }

    @Override
    public Object delete(Object obj) {

        logger.info("用户追踪删除");
        return false;
    }


}
