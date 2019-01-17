package com.dataprocess.core.factory;

import com.dataprocess.core.common.ClazzUtils;
import com.dataprocess.core.common.CoreConst;
import com.dataprocess.core.handler.CoreHandler;
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
    public CoreHandler createCoreHandler(String dataBase, String table) throws ClassNotFoundException, NoSuchMethodException {
        List<CoreHandler> coreHandlers;
        List<Object> objectList= ClazzUtils.getCalsss(
                CoreConst.EXECUTE_PACKAGE_PREFIX+dataBase+"."+table);
        if(null!=objectList&&objectList.size()>=0){
            coreHandlers=new ArrayList<>();
            for (int x=0;x<objectList.size();x++){
                if(objectList.get(x) instanceof  CoreHandler){
                    coreHandlers.add((CoreHandler)objectList.get(x));
                }
            }
            if(null!=coreHandlers&&coreHandlers.size()>=0){
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


}
