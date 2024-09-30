package com.syf.chat.common.config;

import com.syf.chat.thread.CustomThreadFactory;

import java.util.concurrent.*;

/**
 * 线程池配置类
 */
public class ThreadPoolConfig {
    private int corePoolSize = 10;              // 核心线程池大小

    private int maxPoolSize = 10;               // 最大线程池大小

    private long keepAliveTime = 60;            // 空闲线程存活时间

    private TimeUnit unit = TimeUnit.SECONDS;   // 空闲线程存活时间单位(秒)

    private BlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(100);  //工作队列

    private ThreadFactory threadFactory = new CustomThreadFactory("线程");     //线程工厂

    private RejectedExecutionHandler handler = new ThreadPoolExecutor.DiscardPolicy();  //拒绝策略

    public BlockingQueue<Runnable> getQueue() {
        return queue;
    }

    public void setQueue(BlockingQueue<Runnable> queue) {
        this.queue = queue;
    }

    public ThreadFactory getThreadFactory() {
        return threadFactory;
    }

    public void setThreadFactory(ThreadFactory threadFactory) {
        this.threadFactory = threadFactory;
    }

    public RejectedExecutionHandler getHandler() {
        return handler;
    }

    public void setHandler(RejectedExecutionHandler handler) {
        this.handler = handler;
    }

    public int getCorePoolSize() {
        return corePoolSize;
    }

    public void setCorePoolSize(int corePoolSize) {
        this.corePoolSize = corePoolSize;
    }

    public int getMaxPoolSize() {
        return maxPoolSize;
    }

    public void setMaxPoolSize(int maxPoolSize) {
        this.maxPoolSize = maxPoolSize;
    }

    public long getKeepAliveTime() {
        return keepAliveTime;
    }

    public void setKeepAliveTime(long keepAliveTime) {
        this.keepAliveTime = keepAliveTime;
    }

    public TimeUnit getUnit() {
        return unit;
    }

    public void setUnit(TimeUnit unit) {
        this.unit = unit;
    }
}
