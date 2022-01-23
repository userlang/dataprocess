package com.dataprocess.core.template;
/**
 *  Template responsibility chain
 * @Author junnan.lang
 * @Date 2022/1/19 11:21
 * @Version 1.0
 */
public abstract class ChainTemplate {

    private ChainTemplate successor;

    public void setSuccessor(ChainTemplate successor) {
     this.successor = successor;
    }
    protected ChainTemplate getNext(){
        if(null==successor){
            throw new RuntimeException(" getNext  There are no more executable filters  ");
        }
        return successor;
    }

    // *  Logic of * table insertion under library
    public abstract Object insert(Object obj);

    // *  Logic for modifying the * table under the library
    public abstract Object update(Object obj);

    // *  Logic for deleting the * table under the library
    public abstract Object delete(Object obj);

    // *  Exception logic of * table insertion under library
    public abstract void insertRollBack(Object obj);

    // *  Exception logic for modification of * table under library
    public abstract void updateRollBack(Object obj);

    // *  Exception logic for deleting the * table under the library
    public abstract void deleteRollBack(Object obj);

}
