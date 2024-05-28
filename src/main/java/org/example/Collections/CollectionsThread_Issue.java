package org.example.Collections;

import java.util.ArrayList;
import java.util.List;

public class CollectionsThread_Issue {

    public static void main(String[] args) throws InterruptedException {
        CollectionsThread_Issue t = new CollectionsThread_Issue();
        List<Integer> numList = new ArrayList<>();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<1000; i++) {
                    numList.add(i);
                    System.out.println("Added value " + i + " to thread " + Thread.currentThread().getName());
                }
            }
        });


        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<1000; i++) {
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
