package com.dataprocess.core.template;
/**
 * Resolve processing policy factory
 * @Author junnan.lang
 * @Date 2022/1/19 11:21
 * @Version 1.0
 */
public abstract class CoreTemplate {

    private CoreTemplate successor;

    public void setSuccessor(CoreTemplate successor) {
     this.successor = successor;
    }
    protected CoreTemplate getNext(){
        if(null==successor){
            throw new RuntimeException(" getNext  There are no more executable filters  ");
        }
        return successor;
    }

    // * 库下的 * 表插入的逻辑
    public abstract Object insert(Object obj);

    // * 库下的 * 表修改的逻辑
    public abstract Object update(Object obj);

    // * 库下的 * 表删除的逻辑
    public abstract Object delete(Object obj);

    // * 库下的 * 表插入的逻辑
    public abstract void insertRollBack(Object obj);

    // * 库下的 * 表修改的逻辑
    public abstract void updateRollBack(Object obj);

    // * 库下的 * 表删除的逻辑
    public abstract void deleteRollBack(Object obj);

}
