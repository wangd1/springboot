package com.wangdi.springboot13fastdfs;

import java.util.Random;

/**
 * @author dwang
 * @create 2020-03-05 9:24
 */
public class Test {

    public static void main(String[] args) {
        System.out.println("=========>"+Son.num);
    }
}

class Parent{
    public final static int num = new Random().nextInt();
    static{
        System.out.println("=========>2");
    }
}

class Son extends Parent{
    static {
        System.out.println("=========>3");
    }
}
