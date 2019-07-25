package com.customthreadpool;


public interface DenyPolicy<T> {

    void reject(T runnable, ThreadPool threadPool);

    //该拒接策略会直接将任务丢弃
    class DiscardDenyPolicy implements DenyPolicy<Runnable>{

        @Override
        public void reject(Runnable runnable, ThreadPool threadPool) {
            System.out.println(runnable + "do nothing");
        }
    }

    //该拒绝策略会向任务提交者抛出异常
    class AbortDenyPolicy implements DenyPolicy<Runnable>{

        @Override
        public void reject(Runnable runnable, ThreadPool threadPool) {
            throw new RunnbaleDenyException("The runnbale " + runnable + " will be abort.");
        }
    }

    //该拒绝策略会使用任务在提交者所在的线程中执行任务
    class RunnerDenyPolicy implements DenyPolicy<Runnable>{

        @Override
        public void reject(Runnable runnable, ThreadPool threadPool) {
            if (!threadPool.isShutdown()){
                runnable.run();
            }
        }
    }
}
