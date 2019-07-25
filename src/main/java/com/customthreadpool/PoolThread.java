package com.customthreadpool;

public class PoolThread extends Thread {


    private  BlockingQueue taskQueue = null;

    private boolean isStopped = false;

    public PoolThread(BlockingQueue taskQueue) {
        this.taskQueue = taskQueue;
    }

    public void run(){
        while(!isStopped() && !Thread.currentThread().isInterrupted()){
            try{
                //从任务队列获取任务并执行
                Runnable runnable = (Runnable) taskQueue.dequeue();
                runnable.run();
            } catch(Exception e){
                isStopped = true;
                break;
            }
        }
    }

    public synchronized void doStop(){
        isStopped = true;
        this.interrupt();
    }

    public synchronized boolean isStopped(){
        return isStopped;
    }
}
