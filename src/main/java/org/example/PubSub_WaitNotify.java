package org.example;

import java.util.*;

public class PubSub_WaitNotify {

    private final Object lock = new Object();

    private List<Integer> numList = new ArrayList<>();

    private static final int UPPER_LIMIT = 5;

    private static final int LOWER_LIMIT = 0;

    private int value = 0;


    private void consumer() throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {

            public void run() {

                synchronized (lock) {
                    while (true) {

                        if (numList.size() == LOWER_LIMIT) {
                            try {
                                lock.wait();
                                System.out.println("Waiting for producer to produce");
                            } catch (Exception e) {

                            }
                        } else {
                            try {
                                System.out.println("Consumed item " + numList.remove(numList.size() - 1));
                                lock.notify();
                            } catch (Exception e) {

                            }


                        }

                        try {
                            Thread.sleep(1000);
                        } catch (Exception e) {

                        }

                    }

                }

            }

        });
        t1.start();

    }

    private void producer() throws InterruptedException {
        Thread t2 = new Thread(new Runnable() {

            public void run() {

                synchronized (lock) {
                    while (true) {

                        if (numList.size() == UPPER_LIMIT) {


                            try {
                                value = 0;
                                lock.wait();
                            } catch (Exception e) {

                            }
                        } else {


                            try {
                                value++;
                                numList.add(value);
                                lock.notify();
                                System.out.println("Produced value " + value);
                            } catch (Exception e) {

                            }

                        }
                        try {
                            Thread.sleep(1000);
                        } catch (Exception e) {

                        }
                    }


                }

            }

        });
        t2.start();
    }

    public static void main(String args[]) {
        try {
            PubSub_WaitNotify my = new PubSub_WaitNotify();
            my.consumer();
            my.producer();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
