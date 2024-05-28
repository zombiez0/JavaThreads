package org.example;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockExample {

    private Lock lock = new ReentrantLock();

    private int num = 0;

    private void increment(String threadName) {
        lock.lock();
        try {
            for(int i=0; i<10000; i++) {
                num++;
            }
        }catch (Exception e) {

        }finally {
            lock.unlock();
            System.out.println(threadName + " " + num);
        }
    }

    public static void main(String args[]) {

        ReentrantLockExample re = new ReentrantLockExample();

        Thread t1  = new Thread(new Runnable() {
            @Override
            public void run() {
                re.increment("t1");
            }
        });
        t1.start();

        Thread t2  = new Thread(new Runnable() {
            @Override
            public void run() {
                re.increment("t2");
            }
        });
        t2.start();

        try{
            t1.join();
            t2.join();
        }catch (Exception e) {

        }

    }


}
