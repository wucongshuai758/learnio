package com.wcs.learn.parallel;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author 吴聪帅
 * @Description
 * @Date : 下午2:44 2019/3/5 Modifyby:
 **/
public class CompletableFutureCase {
    public static void completedFutureExample() {
        CompletableFuture cf = CompletableFuture.completedFuture("message");
        System.out.println("cf isDone:" + cf.isDone());
        System.out.println("Equals " + " message " + cf.getNow(null));
    }

    static void runAsyncExample() {
        CompletableFuture cf = CompletableFuture.runAsync(() -> {
           System.out.println("assertTrue "+Thread.currentThread().isDaemon());
            try {
                Thread.sleep(3000L);
                System.out.println("finish ");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println("cf isDone:" + cf.isDone());
        try {
            Thread.sleep(10000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("cf isDone:" + cf.isDone());
    }

   public static void thenApplyExample() {
       CompletableFuture cf = CompletableFuture.completedFuture("message").thenApply(s -> {
           System.out.println("assertFalse("+Thread.currentThread().isDaemon()+")");
           try {
               Thread.sleep(3000L);
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
           return s.toUpperCase();
       });
       System.out.println("isEqual :" +"MESSAGE".equals(cf.getNow(null)));
   }

    public static void thenApplyAsyncExample() {
        CompletableFuture cf = CompletableFuture.completedFuture("message").thenApplyAsync(s -> {
            System.out.println("assertTrue(" + Thread.currentThread().isDaemon() +")");
            try {
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return s.toUpperCase();
        });
        System.out.println("assertNull("+cf.getNow(null)+")");
        System.out.println("assertEquals("+ "MESSAGE".equals(cf.join())+")");
    }

    public static ExecutorService executor = Executors.newFixedThreadPool(3, new ThreadFactory() {
        int count = 1;

        @Override
        public Thread newThread(Runnable runnable) {
            return new Thread(runnable, "custom-executor-" + count++);
        }
    });

    public static void thenApplyAsyncWithExecutorExample() {
        CompletableFuture cf = CompletableFuture.completedFuture("message").thenApplyAsync(s -> {
            System.out.println("assertTrue("+Thread.currentThread().getName().startsWith("custom-executor-")+")");
            System.out.println("assertFalse("+Thread.currentThread().isDaemon()+")");
            try {
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return s.toUpperCase();
        }, executor);

        System.out.println("assertNull("+cf.getNow(null)+")");
        System.out.println("assertEquals("+"MESSAGE".equals(cf.join()));
    }

    static void thenAcceptExample() {
        StringBuilder result = new StringBuilder();
        CompletableFuture.completedFuture("thenAccept message").thenApply(s -> result.append("nihao"))
            .thenAccept(s -> result.append(s));
        System.out.println("assertTrue("  +  (result.toString()));
    }

    static void thenAcceptAsyncExample() {
        StringBuilder result = new StringBuilder();
        CompletableFuture cf = CompletableFuture.completedFuture("thenAcceptAsync message")
            .thenAcceptAsync(s -> result.append(s));
        System.out.println("assertTrue("  +  (result.toString()));
        cf.join();
        System.out.println("assertTrue("  +  (result.toString()));
    }

    static void runAfterBothExample() {
        String original = "Message";
        StringBuilder result = new StringBuilder();
        CompletableFuture.completedFuture(original).thenApply(s -> {
            System.out.println("1");
            result.append(s.toUpperCase());
            return s.toUpperCase();
        }).runAfterBoth(
            CompletableFuture.completedFuture(original).thenApply(s -> {
                System.out.println("2");
                result.append(s.toUpperCase());
                return s.toUpperCase();
            }),
            () -> {
                try {
                    Thread.sleep(3000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                result.append("done");
            });
        System.out.println("assertTrue("+"Result was empty " + result.toString());
    }

    private static void thenApply() throws Exception {
        CompletableFuture<Long> future = CompletableFuture.supplyAsync(new Supplier<Long>() {
            @Override
            public Long get(){
                long result = new Random().nextInt(100);
                System.out.println("result1="+result);
                System.out.println("threadname:"+Thread.currentThread().getName());


                return result;
            }
        }).thenApplyAsync(new Function<Long, Long>() {
            @Override
            public Long apply(Long t) {
                long result = t*5;
                System.out.println("result2="+result);
                System.out.println("threadname:"+Thread.currentThread().getName());
                return result;
            }
        });

        long result = future.get();
        System.out.println(result);
    }

    private static void thenCombine() throws Exception {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                System.out.println("threadname:"+Thread.currentThread().getName());
                return "hello";
            }
        });
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                System.out.println("threadname:"+Thread.currentThread().getName());
                return "hello";
            }
        });
        CompletableFuture<String> result = future1.thenCombine(future2, new BiFunction<String, String, String>() {
            @Override
            public String apply(String t, String u) {
                return t+" "+u;
            }
        });
        System.out.println(result.get());
    }

    public static void main(String[] args) {
        try {
            thenCombine();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
