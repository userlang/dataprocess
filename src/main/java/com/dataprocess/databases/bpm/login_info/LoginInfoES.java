package com.dataprocess.databases.bpm.login_info;

import com.dataprocess.core.template.CoreTemplate;
import com.dataprocess.databases.godnan.u_user_tracking.UserTrackingES;
import com.dataprocess.es.dao.LoginInfoDao;
import com.dataprocess.es.entity.LoginInfoEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class LoginInfoES extends CoreTemplate {
    private final static Logger logger = LoggerFactory.getLogger(LoginInfoES.class);

    @Resource
    private LoginInfoDao loginInfoDao;

    @Override
    public Object insert(Object obj) {
        List<Map<String,Object>> list=(List<Map<String,Object>>)obj;
        if(null!=list&&list.size()>0){
            List<LoginInfoEntity> entitys=new ArrayList<>();
            LoginInfoEntity entity=null;
            for (Map<String,Object> item: list  ) {
                entity=new LoginInfoEntity();
                entity.setId(Integer.parseInt(item.get("id")+""));
                entity.setLoginAccount(item.get("login_account")+"");
                entity.setAccountType(Integer.parseInt(item.get("account_type")+""));;
                entity.setLoginState(Integer.parseInt(item.get("login_state")+""));
                entity.setLoginMessage(item.get("login_message")+"");
                entitys.add(entity);
            }
            loginInfoDao.saveAll(entitys);
            return true;
        }
        return false;
    }

    @Override
    public Object update(Object obj) {
        //1当前过滤器 数据更新到ES
//        List<Map<String,Object>> list=(List<Map<String,Object>>)obj;
//        if(null!=list&&list.size()>0){
//            List<LoginInfoEntity> entitys=new ArrayList<>();
//            LoginInfoEntity entity=null;
//            for (Map<String,Object> item: list  ) {
//                entity=new LoginInfoEntity();
//                entity.setId(Integer.parseInt(item.get("id")+""));
//                entity.setLoginAccount(item.get("login_account")+"");
//                entity.setAccountType(Integer.parseInt(item.get("account_type")+""));;
//                entity.setLoginState(Integer.parseInt(item.get("login_state")+""));
//                entity.setLoginMessage(item.get("login_message")+"");
//                entitys.add(entity);
//            }
//            loginInfoDao.saveAll(entitys);
//            //下一个过滤器 去执行数据更新 Redis操作
//            System.out.println("正在执行es修改");
//            getNext().update("111");
//            return true;
//        }
        getNext().update("111");
        return false;
    }

    @Override
    public Object delete(Object obj) {
        List<Map<String,Object>> list=(List<Map<String,Object>>)obj;
        if(null!=list&&list.size()>0){
            List<LoginInfoEntity> entitys=new ArrayList<>();
            LoginInfoEntity entity=null;
            for (Map<String,Object> item: list  ) {
                entity=new LoginInfoEntity();
                entity.setId(Integer.parseInt(item.get("id")+""));
                entity.setLoginAccount(item.get("login_account")+"");
                entity.setAccountType(Integer.parseInt(item.get("account_type")+""));;
                entity.setLoginState(Integer.parseInt(item.get("login_state")+""));
                entity.setLoginMessage(item.get("login_message")+"");
                entitys.add(entity);
            }
            loginInfoDao.deleteAll(entitys);
            return true;
        }
        return false;
    }

    @Override
    public void insertRollBack(Object obj) {


    }

    @Override
    public void updateRollBack(Object obj) {

        logger.info("logininfo es updateRollBack");

    }

    @Override
    public void deleteRollBack(Object obj) {

    }
}
