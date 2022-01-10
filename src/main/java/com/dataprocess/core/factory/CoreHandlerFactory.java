package com.dataprocess.core.factory;

import com.dataprocess.core.common.ClazzUtils;
import com.dataprocess.core.common.CoreConst;
import com.dataprocess.core.handler.CoreHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

        List<Object> list= ClazzUtils.getCalsss(
                CoreConst.ExecutePackagePreFix+dataBase+"."+table);
        if(null==list||list.size()<=0){
            return null;

        }
        for (int i=0;i<list.size()-1;i++)
        {
            int j=i+1;
            ((CoreHandler)list.get(i)).setSuccessor((CoreHandler)list.get(j));

        }
        return (CoreHandler) list.get(0);
    }


}
