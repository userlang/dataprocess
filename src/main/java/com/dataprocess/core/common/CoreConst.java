package com.dataprocess.core.common;


public interface CoreConst {
    //////////////////////////////////////////////////////////
    /////////////////////mq输送的数据类型///////////////////////
    //本次操作的数据库名称
    public static final String DATABASE="database";
    //本次操作的表名称
    public static final String TABLE="table";
    //本次操作的类型
    public static final String TYPE="type";
    //本次操作的数据
    public static final String DATA="data";
    //数据库ddl
    public static final String ISDDLL="isDdl";
    //////////////////////////////////////////////////////////

    ///////////////数据库处理逻辑执行 包前缀
    public static final String EXECUTE_PACKAGE_PREFIX="com.dataprocess.databases.";
}
