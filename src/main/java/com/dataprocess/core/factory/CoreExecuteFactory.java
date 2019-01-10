package com.dataprocess.core.factory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dataprocess.core.common.CoreConst;
import com.dataprocess.core.handler.CoreHandler;
import io.netty.util.internal.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.el.MethodNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * 处理Mq接收到的消息工厂
 */
public  class CoreExecuteFactory {
    private final static Logger logger = LoggerFactory.getLogger(CoreExecuteFactory.class);


    private CoreExecuteFactory(){}

    private static volatile CoreExecuteFactory instance;


    private  static Set<String> BLACKLIST=new HashSet<>();

    static {
        BLACKLIST.add("QUERY");
    }
    /**
     *
     * @return
     */
    public static CoreExecuteFactory getInstance() {

        if (instance == null) {

            synchronized (CoreExecuteFactory.class) {

                if (instance == null) {

                    instance = new CoreExecuteFactory();
                }
            }
        }
        return instance;
    }
    private static CoreHandlerFactory coreHandlerFactory=CoreHandlerFactory.getInstance();

    /**
     * 解析处理器
     * @param message
     * @return
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     */
    public static boolean execute(String message) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        JSONObject object= JSON.parseObject(message);
        boolean isDDl= (boolean) object.get(CoreConst.ISDDLL);
        if(!isDDl){
            String database= (String) object.get(CoreConst.DATABASE);
            String table = (String) object.get(CoreConst.TABLE);
            String type=(String)object.get(CoreConst.TYPE);
            if(BLACKLIST.contains(type)){
                return true;
            }
            if(StringUtil.isNullOrEmpty(database)){
                throw  new NullPointerException(database);
            }
            if(StringUtil.isNullOrEmpty(table)){
                throw  new NullPointerException(table);
            }
            Object data=object.get(CoreConst.DATA);
            if(null==data){
                throw  new NullPointerException("current execute data is  null");
            }

            return execute( database,table,type,data);
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
            CoreHandler coreHandler=coreHandlerFactory.createCoreHandler(database,table);
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

}
