package com.customthreadpool;

import java.util.concurrent.TimeUnit;

public class ThreadPoolTest {

    public static void main(String[] args) throws InterruptedException {

        final ThreadPool threadPool = new ThreadPool(2 , 6 , 4 , 1000);

        //定义20个任务并且提交到线程池
        for (int i = 0; i < 20; i++) {
            threadPool.execute(() ->{
                try {
                    TimeUnit.SECONDS.sleep(10);
                    System.out.println(Thread.currentThread().getName() + " is running add done");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        while (true){
//            System.out.println("getActiveCount: " + threadPool.getActiveCount());
//            System.out.println("getQueueSize: " + threadPool.getQueueSize());
//            System.out.println("getCoreSize: " + threadPool.getCoreSize());
//            System.out.println("getMaxSize: "+ threadPool.getMaxSize());
//            System.out.println("======================================");
            TimeUnit.SECONDS.sleep(12);
            threadPool.shutdown();
            Thread.currentThread().join();
        }
    }
}
