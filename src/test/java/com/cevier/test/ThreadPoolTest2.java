package com.cevier.test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolTest2 {

    private static final ExecutorService executorService = new ThreadPoolExecutor(2, 3, 1, TimeUnit.MINUTES, new LinkedBlockingQueue<>()) {
        @Override
        protected void afterExecute(Runnable runnable, Throwable throwable) {
            // execute提交
            if (throwable != null)
                System.out.println(throwable.getMessage());
            //如果r的实际类型是FutureTask 那么是submit提交的，所以可以在里面get到异常
            if (runnable instanceof FutureTask) {
                try {
                    Future<?> future = (Future<?>) runnable;
                    //get获取异常
                    future.get();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    };


    public static void main(String[] args) throws InterruptedException {
        List<Integer> l = Arrays.asList(1, 2, 0, 3);
        for (Integer i : l) {
            executorService.submit(() -> System.out.println(10 / i));
        }
        Thread.sleep(10_0000L);
    }

}