package org.example;

public class SynchronizedIssueSolution_CustomObject {

    //Instead of synchronized keyword use object lock

    public static Object lock1 = new Object();
    public static Object lock2 = new Object();

    public static void method1() {
        synchronized(lock1) {
            try{
                Thread.sleep(5000);
                System.out.println("From thread 1 ");
            }catch (Exception e) {

            }
        }

    }

    public static synchronized void method2() {
        synchronized(lock2) {
            System.out.println("From thread 2 ");
        }
    }

    public static void main(String args[]) {
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
