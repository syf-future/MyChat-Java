package com.syf.chat.thread;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 自定义线程工厂
 */
public class CustomThreadFactory implements ThreadFactory {
    private final AtomicInteger threadNumber = new AtomicInteger(1);
    private final String namePrefix;
    private boolean daemon = false;   //设置是否为守护线程

    public CustomThreadFactory(String namePrefix) {
        this.namePrefix = namePrefix;
    }

    public CustomThreadFactory(String namePrefix, boolean daemon) {
        this.namePrefix = namePrefix;
        this.daemon = daemon;
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r);
        thread.setName(namePrefix + "-thread-" + threadNumber.getAndIncrement());
        thread.setDaemon(daemon);
        return null;
    }
}
