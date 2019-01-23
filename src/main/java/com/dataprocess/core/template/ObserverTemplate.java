package com.dataprocess.core.template;

public abstract class ObserverTemplate {


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
