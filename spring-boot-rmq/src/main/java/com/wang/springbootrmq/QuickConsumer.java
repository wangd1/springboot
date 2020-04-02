package com.wang.springbootrmq;

/**
 * @author dwang
 * @create 2020-03-05 9:48
 */
public class QuickConsumer {

    public static void main(String[] args) throws ClassNotFoundException {

        Class<?> preClass = Class.forName("java.util.HashMap");
        Class<?>[] classes = preClass.getClasses();
        for (Class<?> classs : classes) {
            System.out.println(classs);
        }

    }
}
