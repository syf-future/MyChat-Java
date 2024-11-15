package com.syf.serverControl.controller;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;

public class Text {
    public static ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();

    public static void main(String[] args) {
        System.out.println("20240805".compareTo("20240901"));
    }


}
