package org.example;

public class S1_SynchronizedKeywordIssue1 {

    //Below code has class level lock
    public static synchronized void method1() {
        try{
            Thread.sleep(5000);
            System.out.println("From thread 1 ");
        }catch (Exception e) {

        }
    }

    public static synchronized void method2() {
        System.out.println("From thread 2 ");
    }

    public static void main(String[] args) {
        System.out.println("Hello world!");

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                method1();
            }
        });
        t1.start();

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                method2();
            }
        });
        t2.start();
    }
}