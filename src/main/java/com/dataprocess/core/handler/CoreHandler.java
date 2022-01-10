package com.dataprocess.core.handler;

public abstract class CoreHandler {

    protected CoreHandler successor;

    public void setSuccessor(CoreHandler successor) {
     this.successor = successor;
    }


    // * 库下的 * 表插入的逻辑
    public abstract Object insert(Object obj);

    // * 库下的 * 表修改的逻辑
    public abstract Object update(Object obj);

    // * 库下的 * 表删除的逻辑
    public abstract Object delete(Object obj);



}
