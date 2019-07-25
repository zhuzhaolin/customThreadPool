package com.customthreadpool;

public interface Service {

    //关闭线程池
    void shutdown();

    //查看线程池是否已经被shutdown
    boolean isShutdown();

    //提交任务到线程池
    void execute(Runnable runnable);

    //获取线程池的初始化大小
    int getInitSize();

    //获取线程池最大的线程数
    int getMaxSize();

    //获取线程池核心线程梳理
    int getCoreSize();

    //获取线程池中活跃线程的数量大小
    int getQueueSize();

    //获取线程池中用于缓存任务队列的大小
    int getActiveCount();
}
