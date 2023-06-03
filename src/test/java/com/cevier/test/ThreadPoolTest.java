package com.cevier.test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolTest {
    private static final ThreadFactory threadFactory = new ExceptionThreadFactory(new MyExceptionHandler());

    private static final ExecutorService executorService = new ThreadPoolExecutor(2, 3, 1, TimeUnit.MINUTES, new LinkedBlockingQueue<>(), threadFactory);



    public static class ExceptionThreadFactory implements ThreadFactory {
        private static final ThreadFactory defaultFactory = Executors.defaultThreadFactory();

        private final Thread.UncaughtExceptionHandler handler;

        public ExceptionThreadFactory(Thread.UncaughtExceptionHandler handler) {
            this.handler = handler;
        }

        @Override
        public Thread newThread(Runnable run) {
            Thread thread = defaultFactory.newThread(run);
            thread.setUncaughtExceptionHandler(handler);
            return thread;
        }
    }

    public static class MyExceptionHandler implements Thread.UncaughtExceptionHandler {
        @Override
        public void uncaughtException(Thread thread, Throwable t) {
//            t.printStackTrace();
            System.out.println(t.getMessage());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        List<Integer> l = Arrays.asList(1, 2, 0, 3);
        for (Integer i : l) {
            executorService.execute(() -> System.out.println(10 / i));
        }
//        Thread.sleep(10_0000L);
    }

}