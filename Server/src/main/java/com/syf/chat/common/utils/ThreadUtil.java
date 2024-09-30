package com.syf.chat.common.utils;

import com.syf.chat.common.config.ThreadPoolConfig;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * 线程工具类
 */
public class ThreadUtil {
    public static ThreadPoolExecutor createThreadPool(ThreadPoolConfig config) {
        return new ThreadPoolExecutor(
                config.getCorePoolSize(),
                config.getMaxPoolSize(),
                config.getKeepAliveTime(),
                config.getUnit(),
                config.getQueue(),
                config.getThreadFactory(),
                config.getHandler()
        );
    }
}
