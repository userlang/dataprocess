package com.dataprocess.core.common;

import java.io.File;
import java.io.FileFilter;
import java.net.URL;
import java.util.*;

public class ClazzUtils {

    /**
     * 获取类加载器
     */
    public static ClassLoader getClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }
    /**
     * 加载类
     */
    public static Class<?> loadClass(String className, boolean isInitialized) {
        Class<?> cls;
        try {
            cls = Class.forName(className, isInitialized, getClassLoader());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return cls;
    }
    /**
     * 加载类（默认将初始化类）
     */
    public static Class<?> loadClass(String className) {
        return loadClass(className, true);
    }

    /**
     * 获取指定包名下的所有类
     */
    public static List<String> getClassSet(String packageName) {
        List<String> classSet = new ArrayList<>();
        try {
            Enumeration<URL> urls = getClassLoader().getResources(packageName.replace(".", "/"));
            while (urls.hasMoreElements()) {
                URL url = urls.nextElement();
                if (url != null) {
                    String protocol = url.getProtocol();
                    if (protocol.equals("file")) {
                        String packagePath = url.getPath().replaceAll("%20", " ");
                        addClass(classSet, packagePath, packageName);
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return classSet;
    }

    private static void addClass(List<String> classSet, String packagePath, String packageName) {
        File[] files = new File(packagePath).listFiles(new FileFilter() {
            public boolean accept(File file) {
                return (file.isFile() && file.getName().endsWith(".class")) || file.isDirectory();
            }
        });
        for (File file : files) {
            String fileName = file.getName();
            if (file.isFile()) {
                String className = fileName.substring(0, fileName.lastIndexOf("."));
                classSet.add(className);
            }
        }
    }


    public static List<Object> getCalsss(String packageName) throws ClassNotFoundException {
        List<String> param=  getClassSet(packageName);

        List<Object> result=new ArrayList<>();
        if(null!=param&&param.size()>0){

            for (String clas: param ) {
                //result.add( loadClass(clas));
                result.add( SpringUtils.getBean(StringUtils.lowerFirst(clas)));
            }
        }
        return result;
    }


}