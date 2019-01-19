package com.dataprocess.core.factory;

import com.dataprocess.core.common.CoreConst;
import com.dataprocess.core.adapter.CoreData;
import com.dataprocess.core.template.CoreTemplate;
import io.netty.util.internal.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.el.MethodNotFoundException;
import java.lang.reflect.InvocationTargetException;
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



    /**
     * 解析处理器
     * @param data
     * @return
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     */
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
            if(null==data){
                throw  new NullPointerException("current execute data is  null");
            }

            return execute( database,table,type,dataObj);
        }

    return true;
    }

    /**
     * 策略处理器
     * @param database
     * @param table
     * @param data
     * @param type
     * @return
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public static boolean execute( String database,String table, String type,Object data) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        try{
            CoreTemplate coreHandler=coreHandlerFactory.createCoreTemplate(database,table);
            if(null==coreHandler){
                // logger.info(" No implementation class found for "+database+"."+table);
                return true;
            }
            Method method  = coreHandler.getClass().getMethod(type.toLowerCase(),Object.class);
            if(null==method){
                throw new MethodNotFoundException("  The method of this "+coreHandler.getClass()+" cannot be found   ");
            }
            method.invoke(coreHandler,data);
            return true;

        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
    private static volatile CoreStrategyFactory instance;



    /**
     *
     * @return
     */
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
