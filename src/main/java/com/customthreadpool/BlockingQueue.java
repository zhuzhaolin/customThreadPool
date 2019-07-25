package com.customthreadpool;

import java.util.LinkedList;
import java.util.List;

public class BlockingQueue<T> {

    /**
     * 使用链表实现一个阻塞队列(数据结构定义数据存储和获取方式，所以只要满足这两点，阻塞队列可以用链表，也可以使用数组等来实现)
     */
    private List<T> queue = new LinkedList();
    /**
     * limit用来限制提交任务的最大数，默认10
     */
    private int limit = 10;


    /**
     * 拒接策略
     */
    private DenyPolicy denyPolicy;

    private ThreadPool threadPool;


    public BlockingQueue(int limit, DenyPolicy denyPolicy, ThreadPool threadPool) {
        this.limit = limit;
        this.denyPolicy = denyPolicy;
        this.threadPool = threadPool;
    }

    /**
     * @param item
     * @throws InterruptedException enqueue是一个同步方法，当任务到达上限，便会调用wait方法进行阻塞，否则将任务放入队列中，并唤醒dequeue()任务线程
     */
    public synchronized void enqueue(T item) {
        //若果队列到达最大容量，调用拒接策略
        if (this.queue.size() >= this.limit) {
            denyPolicy.reject(item, threadPool);
        }
        if (this.queue.size() <= limit) {
            this.notifyAll();
        }
        this.queue.add(item);
    }


    /**
     * @return
     * @throws InterruptedException dequeue也是一个同步方法，当队列中没有任务时便会调用wait方法进入阻塞，当任务到达最大容量是唤醒其他dequeue()线程
     *                              ，并出列一个任务。
     */
    public synchronized T dequeue()
            throws InterruptedException {
        while (this.queue.size() == 0) {
            this.wait();
        }
        if (this.queue.size() == this.limit) {
            this.notifyAll();
        }

        return this.queue.remove(0);
    }

    public synchronized int size() {
        return queue.size();
    }

}
