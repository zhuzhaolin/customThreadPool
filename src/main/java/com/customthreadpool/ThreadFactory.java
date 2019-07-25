package com.customthreadpool;

@FunctionalInterface
public interface ThreadFactory {
    Thread createThread(Runnable runnable);
}
