package com.syf.chat.common.utils;

import java.net.Socket;
import java.util.Map;

public class ThreadLocalUtil {
    private static final ThreadLocal<Map<String, Socket>> threadLocal = new ThreadLocal<>();

    public static Map<String, Socket> get()
    {
        return threadLocal.get();
    }
    public static void put(Map<String, Socket> map)
    {
        threadLocal.set(map);
    }

    /**
     * 清空 ThreadLocal
     */
    public static void remove()
    {
        threadLocal.remove();
    }
}
