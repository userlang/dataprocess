package com.dataprocess.core.common;

public class StringUtils {

    public  static  String lowerFirst(String str){
        char[] c=str.toCharArray();
        c[0]+=32;
        return String.valueOf(c);
    }



}
