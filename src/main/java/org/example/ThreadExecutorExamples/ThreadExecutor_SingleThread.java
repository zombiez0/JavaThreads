package org.example.ThreadExecutorExamples;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


public class ThreadExecutor_SingleThread {

    public class Task implements Runnable {

        private int taskId = 0;
        public Task(int id) {
            this.taskId = id;
        }

        @Override
        public void run() {
            System.out.println("Thread with task id = " + this.taskId);
            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String... args) {
        ThreadExecutor_SingleThread te = new ThreadExecutor_SingleThread();
        te.executeThreads();

    }

    private void executeThreads() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for(int i=0;i <5; i++) {
            executorService.execute(new Task(i));
        }

        //We prevent the executor to execute any further tasks
        executorService.shutdown();

        //Terminate actual running tasks
        try{
            if(!executorService.awaitTermination(1000, TimeUnit.MILLISECONDS)) {
                    executorService.shutdown();
            }
        }catch (InterruptedException e) {

        }

    }


}
