package com.dataprocess.core.factory;

import com.dataprocess.core.common.CoreConst;
import com.dataprocess.core.adapter.CoreData;
import com.dataprocess.core.handler.Invocation;
import com.dataprocess.core.subject.CoreSubject;
import com.dataprocess.core.template.ChainTemplate;
import io.netty.util.internal.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.el.MethodNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Resolve processing policy factory
 * @Author junnan.lang
 * @Date 2022/1/19 11:21
 * @Version 1.0
 */
public  class CoreStrategyFactory {
    private final static Logger logger = LoggerFactory.getLogger(CoreStrategyFactory.class);


    private CoreStrategyFactory(){}


    public static boolean analysis(CoreData data) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {

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


    public static boolean execute( String database,String table, String type,Object data) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        try{
            List<Object> tableListClass =coreHandlerFactory.createTableListClass(database,table);
            if(null==tableListClass||(null!=tableListClass&&tableListClass.size()==0)){
                // logger.info(" No implementation class found for "+database+"."+table);
                return true;
            }
            CoreSubject subjectHandler =coreHandlerFactory.createObserverTemplate(tableListClass);
            ChainTemplate chainHandler= coreHandlerFactory.createChainTemplate(tableListClass);
            if(null!=subjectHandler){
                subjectHandler.notify(data,type);
            }
            if(null!=chainHandler){

                Method method  = chainHandler.getClass().getMethod(type.toLowerCase(),Object.class);
                if(null==method){
                    throw new MethodNotFoundException("  The method of this "+chainHandler.getClass()+" cannot be found   ");
                }
                method.invoke(chainHandler,data);
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
    private static CoreTrusteeshipFactory coreHandlerFactory= CoreTrusteeshipFactory.getInstance();
}
