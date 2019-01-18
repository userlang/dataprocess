package com.dataprocess.demo1;

public class HelloServiceImpl extends HelloService {

    private int i=0;

    public void setI(int i) {
        this.i = i;
    }

    public int getI() {
        return i;
    }

    @Override
    public void insert() {
        System.out.println("正在执行HelloServiceImpl insert目标方法"+i);
        int [] aa=new int[2];
      //  int b=aa[5];

    }

    @Override
    public void update(Object obj) {
        System.out.println("正在执行HelloServiceImpl update目标方法");
    }

    @Override
    public void insertRollBack(Object obj) {
        System.out.println("正在执行HelloServiceImpl insert回滚");
    }
}
