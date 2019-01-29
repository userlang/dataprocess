package com.dataprocess.core.factory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dataprocess.core.builder.*;
import com.dataprocess.core.common.CoreConst;
import com.dataprocess.core.adapter.CoreData;
import com.dataprocess.core.common.Properties;
import com.dataprocess.core.handler.Invocation;
import com.dataprocess.core.subject.CoreSubject;
import com.dataprocess.core.template.ChainTemplate;
import io.netty.util.internal.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.el.MethodNotFoundException;
import java.lang.reflect.Method;

/**
 * Resolve processing policy factory
 * @Author junnan.lang
 * @Date 2022/1/19 11:21
 * @Version 1.0
 */
public  class CoreStrategyFactory {
    private final static Logger logger = LoggerFactory.getLogger(CoreStrategyFactory.class);


    private CoreStrategyFactory(){}



    public CoreData toData(String message) {
        JSONObject object = JSON.parseObject(message);
        CoreData data=new CoreData();
        boolean isDDl = (boolean) object.get(CoreConst.ISDDLL);
        data.setIsDll(isDDl);
        data.setData(object.get(CoreConst.DATA));
        data.setDatabase((String) object.get(CoreConst.DATABASE));
        data.setTable((String) object.get(CoreConst.TABLE));
        data.setOptType((String) object.get(CoreConst.TYPE));
        return data;
    }

    public static boolean analysis(String message) throws Throwable {
        CoreData data= getInstance().toData(message);
        boolean isDDl= (boolean) data.getIsDll();
        if(!isDDl){
            String database= data.getDatabase();
            String table = data.getTable();
            String type=data.getOptType();
            if(CoreConst.QUERY.equals(type)){
                return true;
            }
            if(StringUtil.isNullOrEmpty(database)){
                throw  new NullPointerException(database);
            }
            if(StringUtil.isNullOrEmpty(table)){
                throw  new NullPointerException(table);
            }
            Object dataObj=data.getData();
            if(null==dataObj){
                throw  new NullPointerException("current execute data is  null");
            }

            return execute( database,table,type,dataObj);
        }

    return true;
    }

    public static boolean execute( String database,String table, String type,Object data) throws Throwable {
        try{
            CoreBuilder builder=null;
            if(Properties.isOPEN_PROXY){
                 builder=new ProxyBuilder(database,table);
            }else {
                 builder=new OrdinaryBuilder(database,table);
            }
            BuilderDirector builderDirector=new BuilderDirector(builder);
            CorePlan plan=builderDirector.build();
            CoreSubject subjectHandler =plan.getCoreSubject();
            ChainTemplate chainHandler= plan.getChainTemplate();
            if(null!=subjectHandler){
                subjectHandler.notify(data,type);
            }
            if(null!=chainHandler){

                Method method  = chainHandler.getClass().getMethod(type.toLowerCase(),Object.class);

                if(null==method){
                    throw new MethodNotFoundException("  The method of this "+chainHandler.getClass()+" cannot be found   ");
                }
                Invocation invocation=new Invocation(chainHandler,chainHandler.getClass().getMethod(type.toLowerCase(),Object.class),data);
                invocation.invoke();

            }

            return true;

        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
    private static volatile CoreStrategyFactory instance;


    public static CoreStrategyFactory getInstance() {

        if (instance == null) {

            synchronized (CoreStrategyFactory.class) {

                if (instance == null) {

                    instance = new CoreStrategyFactory();
                }
            }
        }
        return instance;
    }
}
