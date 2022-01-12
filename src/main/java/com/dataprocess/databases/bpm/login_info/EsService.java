package com.dataprocess.databases.bpm.login_info;

import com.dataprocess.core.common.StringUtils;
import com.dataprocess.core.handler.CoreHandler;
import com.dataprocess.es.dao.LoginInfoDao;
import com.dataprocess.es.entity.LoginInfoEntity;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class EsService extends CoreHandler {

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
                entity.setLoginState((int)item.get("login_state"));
                entity.setLoginMessage(item.get("login_message")+"");
                entitys.add(entity);
            }
            loginInfoDao.deleteAll(entitys);
            return true;
        }
        return false;
    }
}
