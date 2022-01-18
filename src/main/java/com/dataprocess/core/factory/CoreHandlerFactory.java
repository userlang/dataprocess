package com.dataprocess.core.factory;

import com.dataprocess.core.common.ClazzUtils;
import com.dataprocess.core.common.CoreConst;
import com.dataprocess.core.handler.CoreProxyInvocation;
import com.dataprocess.core.handler.InterceptHandler;
import com.dataprocess.core.handler.RollBackHandler;
import com.dataprocess.core.template.CoreTemplate;
import net.sf.cglib.proxy.Proxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class CoreHandlerFactory {
    private final static Logger logger = LoggerFactory.getLogger(CoreHandlerFactory.class);

    private CoreHandlerFactory(){}

    private static volatile CoreHandlerFactory instance;



    /**
     *
     * @return
     */
    public static CoreHandlerFactory getInstance() {

        if (instance == null) {

            synchronized (CoreHandlerFactory.class) {

                if (instance == null) {

                    instance = new CoreHandlerFactory();
                }
            }
        }
        return instance;
    }
    /**
     * 根据数据库和表创建核心工厂
     * @param dataBase
     * @param table
     * @return
     * @throws ClassNotFoundException
     * @throws NoSuchMethodException
     */
    public CoreTemplate createCoreHandler(String dataBase, String table) throws ClassNotFoundException, NoSuchMethodException {
        List<CoreTemplate> coreHandlers;
        List<Object> objectList= ClazzUtils.getCalsss(
                CoreConst.EXECUTE_PACKAGE_PREFIX+dataBase+"."+table);
        RollBackHandler rollBack=new RollBackHandler();
        if(null!=objectList&&objectList.size()>0){
            coreHandlers=new ArrayList<>();
            for (int x=0;x<objectList.size();x++){
                if(objectList.get(x) instanceof CoreTemplate){
                    coreHandlers.add((CoreTemplate) getProxyObject(objectList.get(x),rollBack));
                }
            }
            if(null!=coreHandlers&&coreHandlers.size()>0){
                for (int i=0;i<coreHandlers.size()-1;i++)
                {
                    int j=i+1;
                    coreHandlers.get(i).setSuccessor(coreHandlers.get(j));

                }
                return coreHandlers.get(0);
            }
        }
        return null;

    }

    /**
     * 目标类 handler
     * @param target
     * @param handler
     * @return
     */
    public static Object getProxyObject(Object target, InterceptHandler handler){


        return Proxy.newProxyInstance(target.getClass().getClassLoader(),target.getClass().getInterfaces(),new CoreProxyInvocation(target,handler));
    }

}
