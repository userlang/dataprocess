package com.dataprocess.core.common;
/**
 * @Author junnan.lang
 * @Date 2022/1/19 11:21
 * @Version 1.0
 */
public class StringUtils {

    public  static  String lowerFirst(String str){
        char[] c=str.toCharArray();
        c[0]+=32;
        return String.valueOf(c);
    }



}
