package com.dataprocess.demo1;

public class HelloServiceImpl1 extends HelloService {

    @Override
    public void insert() {
        System.out.println("正在执行HelloServiceImpl1 insert目标方法");
        int [] aa=new int[2];
        int b=aa[5];

    }

    @Override
    public void update(Object obj) {
        System.out.println("正在执行HelloServiceImpl1 update目标方法");
    }

    @Override
    public void insertRollBack(Object obj) {
        System.out.println("正在执行HelloServiceImpl1 insert回滚");
    }
}
