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
    public Object insert(Object obj){
        try{
            return  insertData(obj);
        }catch (Exception e){
            e.printStackTrace();
            insertDataRollBack(obj);
        }
        return  null;
    }
    public Object update(Object obj){
        try{
            return  updateData(obj);
        }catch (Exception e){
            e.printStackTrace();
            updateDataRollBack(obj);
        }
        return  null;
    }
    public Object delete(Object obj){
        try{
            return  deleteData(obj);
        }catch (Exception e){
            e.printStackTrace();
            deleteDataRollBack(obj);
        }
        return  null;
    }

    // *  Logic of * table insertion under library
    public abstract Object insertData(Object obj);

    // *  Logic for modifying the * table under the library
    public abstract Object updateData(Object obj);

    // *  Logic for deleting the * table under the library
    public abstract Object deleteData(Object obj);

    // *  Exception logic of * table insertion under library
    public abstract void insertDataRollBack(Object obj);

    // *  Exception logic for modification of * table under library
    public abstract void updateDataRollBack(Object obj);

    // *  Exception logic for deleting the * table under the library
    public abstract void deleteDataRollBack(Object obj);

}
