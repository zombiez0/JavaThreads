package org.example.ThreadExecutorExamples;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ThreadExecutor_Callable_Future {

    public class Task implements Callable<String> {

        private String taskName;

        public Task(String name) {
            this.taskName = name;
            System.out.println("Task name = " + this.taskName + " from thread " + Thread.currentThread().threadId());
        }

        @Override
        public String call() throws Exception {
            return "id " + Thread.currentThread().threadId();
        }
    }

    public static void main(String args[]) {
        ThreadExecutor_Callable_Future tcf = new ThreadExecutor_Callable_Future();
        tcf.executeThreads();
    }

    private void executeThreads() {
        ExecutorService es = Executors.newFixedThreadPool(2);
        List<Future<String>> values = new ArrayList<>();

        for(int i=0; i<5; i++) {
            values.add(es.submit(new Task("Hello " + i)));
        }
        values.forEach(v-> {
            try {
                System.out.println(v.get());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        });

        //We prevent the executor to execute any further tasks
        es.shutdown();

        //Terminate actual running tasks
        try{
            if(!es.awaitTermination(1000, TimeUnit.MILLISECONDS)) {
                es.shutdown();
            }
        }catch (InterruptedException e) {

        }


    }


}
