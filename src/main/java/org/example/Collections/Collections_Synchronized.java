package org.example.Collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Collections_Synchronized {
    public static void main(String[] args) throws InterruptedException {
        CollectionsThread_Issue t = new CollectionsThread_Issue();
        List<Integer> numList = Collections.synchronizedList(new ArrayList<>());        //Intrinsic Lock, not the most efficient solution because threads have to wait for each other even when they want to execute independent methods

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<50; i++) {
                    numList.add(i);
                    System.out.println("Added value " + i + " to thread " + Thread.currentThread().getName());
                }
            }
        });


        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<50; i++) {
                    numList.add(i);
                    System.out.println("Added value " + i + " to thread " + Thread.currentThread().getName());
                }
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }
}
