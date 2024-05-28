package org.example.Collections;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Collections_CountdownLatch {

    class Worker implements Runnable {

        int id;
        private CountDownLatch countDownLatch;

        public Worker(int id, CountDownLatch countDownLatch) {
            this.id = id;
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            this.doTask();
        }

        private void doTask() {
            try {
                System.out.println("From worker with ID " + this.id);
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                this.countDownLatch.countDown();
            }

        }
    }

    public Collections_CountdownLatch() {

    }

    public static void main(String args[]) {
        Collections_CountdownLatch ct = new Collections_CountdownLatch();
        ct.startWorker();
    }

    private void startWorker() {
        CountDownLatch countDownLatch = new CountDownLatch(5);

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for(int i=0; i<5; i++) {
            executorService.execute(new Worker(i, countDownLatch));
        }
        try{
            countDownLatch.await();
        }catch (Exception e) {

        }

        System.out.println("All threads finished execution");
        executorService.shutdown();
    }


}
