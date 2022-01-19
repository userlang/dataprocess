package com.dataprocess.core.factory;

import com.dataprocess.core.common.ClazzUtils;
import com.dataprocess.core.common.CoreConst;
import com.dataprocess.core.handler.CoreHandler;
import com.dataprocess.core.template.CoreTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.cglib.proxy.Enhancer;

import java.util.ArrayList;
import java.util.List;
/**
 * @Author junnan.lang
 * @Date 2022/1/19 11:21
 * @Version 1.0
 */
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
    public CoreTemplate createCoreTemplate(String dataBase, String table) throws ClassNotFoundException, NoSuchMethodException {
        List<CoreTemplate> coreHandlers;
        List<Object> objectList= ClazzUtils.getCalsss(
                CoreConst.EXECUTE_PACKAGE_PREFIX+dataBase+"."+table);
        if(null!=objectList&&objectList.size()>0){
            coreHandlers=new ArrayList<>();
            for (int x=0;x<objectList.size();x++){
                if(objectList.get(x) instanceof  CoreTemplate){
                    CoreTemplate proxy=(CoreTemplate) createProxyCoreHandler(objectList.get(x));
                    BeanUtils.copyProperties(objectList.get(x), proxy);
                    coreHandlers.add(proxy);
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

    public Object createProxyCoreHandler(Object object){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(object.getClass());
        enhancer.setCallback(new CoreHandler());
       return enhancer.create();

    }

}
