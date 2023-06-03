package com.cevier.test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolTest2 {

    private static final ExecutorService executorService = new ThreadPoolExecutor(2, 3, 1, TimeUnit.MINUTES, new LinkedBlockingQueue<>()) {
        @Override
        protected void afterExecute(Runnable runnable, Throwable throwable) {
            System.out.println(throwable.getMessage());
        }
    };


    public static void main(String[] args) throws InterruptedException {
        List<Integer> l = Arrays.asList(1, 2, 0, 3);
        for (Integer i : l) {
            executorService.execute(() -> System.out.println(10 / i));
        }
        Thread.sleep(10_0000L);
    }

}