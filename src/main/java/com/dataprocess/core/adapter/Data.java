package com.dataprocess.core.adapter;

/**
 * @Author junnan.lang
 * @Date 2022/1/19 12:33
 * @Version 1.0
 */
public class Data {

   private boolean isDll;
   private String database;
   private String table;
   private String optType;
   private Object data;

    public boolean getIsDll() {
        return isDll;
    }

    public void setIsDll(boolean isDll) {
        this.isDll = isDll;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getOptType() {
        return optType;
    }

    public void setOptType(String optType) {
        this.optType = optType;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
