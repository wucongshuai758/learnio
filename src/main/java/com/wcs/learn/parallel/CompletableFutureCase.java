package com.wcs.learn.parallel;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.*;
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

    @Test
    public void runAsyncExample() {
        CompletableFuture cf = CompletableFuture.runAsync(() -> {
            System.out.println("let's do something ");
            try {
                Thread.sleep(3000L);
                System.out.println("finish ");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println("step1 cf isDone:" + cf.isDone());
        try {
            Thread.sleep(10000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("step2 cf isDone:" + cf.isDone());
    }

    static void runAsyncExecutorPoolExample() {
        TestThreadFactory t = new TestThreadFactory("MyThreadFactory");
        CompletableFuture cf = CompletableFuture.runAsync(() -> {
            System.out.println("let's do something the theadName is:" + Thread.currentThread().getName());
            try {
                Thread.sleep(3000L);
                System.out.println("finish the theadName is:" + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, Executors.newCachedThreadPool(t));
        System.out.println("step1 cf isDone:" + cf.isDone());
        try {
            Thread.sleep(10000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("step2 cf isDone:" + cf.isDone());
    }

    public static void supplyAsyncExample() {
        CompletableFuture future = CompletableFuture.supplyAsync(() -> {
            System.out.println("thread start doing something");
            try {
                Thread.sleep(3000L);
                System.out.println("done");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "success";
        });
        try {
            System.out.println("计算结果:" + future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void thenApplyExample() {
        CompletableFuture cf = CompletableFuture.completedFuture("message").thenApply(s -> {
            System.out.println("inner1 thread name :" + Thread.currentThread().getName());
            try {
                Thread.sleep(3000L);
                System.out.println("innert1 thread finish");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return s.toUpperCase();
        });
        try {
            System.out.println("outter thread name :" + Thread.currentThread().getName());
            System.out.println("isEqual :" + "MESSAGE".equals(cf.get().toString()));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public void thenApplyAsyncExample() {
        CompletableFuture cf = CompletableFuture.completedFuture("message").thenApplyAsync(s -> {
            System.out.println("task1 doing... threadName:" + Thread.currentThread().getName());
            try {
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "msg";
        }).thenApplyAsync(s -> {
            System.out.println("task2 doing... threadName:" + Thread.currentThread().getName());
            try {
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return s.toLowerCase() + s.toUpperCase();
        });
        try {
            System.out.println("result:" + cf.get());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void thenComposeExample() {
        CompletableFuture<String> completableFuture1 = CompletableFuture.supplyAsync(() -> {
            //模拟执行耗时任务
            System.out.println("task1 doing... threadName:" + Thread.currentThread().getName());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //返回结果
            return "result1";
        }).thenCompose(result -> CompletableFuture.supplyAsync(() -> {
            //模拟执行耗时任务
            System.out.println("task2 doing...threadName:" + Thread.currentThread().getName());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //返回结果
            return result + " combine " + "result2";
        })).thenApplyAsync(s -> {
            //模拟执行耗时任务
            System.out.println("task3 doing...threadName:" + Thread.currentThread().getName());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //返回结果
            return s + " combine " + "result3";
        });

        try {
            System.out.println(completableFuture1.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAllOfExample() {
        CompletableFuture future1 = CompletableFuture.supplyAsync(() -> {
            //模拟执行耗时任务
            System.out.println("task1 doing... threadName:" + Thread.currentThread().getName());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("task1 finish... threadName:" + Thread.currentThread().getName());
            return "1";
        });

        CompletableFuture future2 = CompletableFuture.supplyAsync(() -> {
            //模拟执行耗时任务
            System.out.println("task2 doing... threadName:" + Thread.currentThread().getName());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("task2 finish... threadName:" + Thread.currentThread().getName());
            return "2";
        });

        CompletableFuture.allOf(future1, future2).thenRun(() -> {
            System.out.println("tash1 and task2 finish... threadName:" + Thread.currentThread().getName());
        }).join();

    }

    @Test
    public void testAnyofExample() {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            //模拟执行耗时任务
            System.out.println("task1 doing... threadName:" + Thread.currentThread().getName());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("task1 finish... threadName:" + Thread.currentThread().getName());
            return "1";
        });

        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            //模拟执行耗时任务
            System.out.println("task2 doing... threadName:" + Thread.currentThread().getName());
            try {
                Thread.sleep(7000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("task2 finish... threadName:" + Thread.currentThread().getName());
            return "2";
        });
        CompletableFuture.anyOf(future1, future2).thenAccept(s -> {
            System.out.println("tash1 and task2 finish... threadName:" + Thread.currentThread().getName());
            System.out.println("result :" + s);
        }).join();

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
            System.out.println("assertTrue(" + Thread.currentThread().getName().startsWith("custom-executor-") + ")");
            System.out.println("assertFalse(" + Thread.currentThread().isDaemon() + ")");
            try {
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return s.toUpperCase();
        }, executor);

        System.out.println("assertNull(" + cf.getNow(null) + ")");
        System.out.println("assertEquals(" + "MESSAGE".equals(cf.join()));
    }

    static void thenAcceptExample() {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            //模拟执行耗时任务
            System.out.println("task2 doing... threadName:" + Thread.currentThread().getName());
            try {
                Thread.sleep(7000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("task2 finish... threadName:" + Thread.currentThread().getName());
            return "2";
        });
        future.thenAccept(value -> {
            System.out.println("task finish,the result is " + value + ",then run accept");
        }).join();

    }

    static void thenAcceptAsyncExample() {
        StringBuilder result = new StringBuilder();
        CompletableFuture cf = CompletableFuture.completedFuture("thenAcceptAsync message")
            .thenAcceptAsync(s -> result.append(s));
        System.out.println("assertTrue(" + (result.toString()));
        cf.join();
        System.out.println("assertTrue(" + (result.toString()));
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
        System.out.println("assertTrue(" + "Result was empty " + result.toString());
    }

    private static void thenApply() throws Exception {
        CompletableFuture<Long> future = CompletableFuture.supplyAsync(new Supplier<Long>() {
            @Override
            public Long get() {
                long result = new Random().nextInt(100);
                System.out.println("result1=" + result);
                System.out.println("threadname:" + Thread.currentThread().getName());

                return result;
            }
        }).thenApplyAsync(new Function<Long, Long>() {
            @Override
            public Long apply(Long t) {
                long result = t * 5;
                System.out.println("result2=" + result);
                System.out.println("threadname:" + Thread.currentThread().getName());
                return result;
            }
        });

        long result = future.get();
        System.out.println(result);
    }

    private static void thenCombineExample() throws Exception {
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
            //模拟执行耗时任务
            System.out.println("task1 doing..thread name:" + Thread.currentThread().getName());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //返回结果
            return 100;
        });

        CompletableFuture<Integer> future2 = future1.thenCombine(
            //第二个任务
            CompletableFuture.supplyAsync(() -> {
                //模拟执行耗时任务
                System.out.println("task2 doing...thread name:" + Thread.currentThread().getName());
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //返回结果
                return 2000;
            }), (result1, result2) -> result1 + result2);

        System.out.println(future2.get());
    }

    public static void testCompletionStage() {
        //   CompletionStage<JSONObject> completionStage =
    }

    public static class TestThreadFactory implements ThreadFactory {
        private String name;
        private List<String> stats = new ArrayList<>();
        private int counter;

        public TestThreadFactory(String name) {
            this.name = name;
            counter = 0;
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r, name + "-Thread-" + counter);
            counter++;
            stats.add(String.format("Created thread %d with name %s on%s\n", t.getId(), t.getName(), new Date()));
            return t;
        }
    }

    public CompletableFuture<Map<Integer, String>> doCompletableFuture(int i) {
        return CompletableFuture.supplyAsync(() -> {
            Map<Integer, String> map = new HashMap<>();
            map.put(i, Thread.currentThread().getName());
            try {
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(JSON.toJSONString(map));
            return map;
        });
    }

    @Test
    public void testFutureList() {
        List<CompletableFuture<Map<Integer, String>>> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(doCompletableFuture(i).toCompletableFuture());
        }
        CompletableFuture future = CompletableFuture.allOf(
            list.toArray((CompletableFuture<Map<Integer, String>>[])new CompletableFuture[list.size()])).thenApply(
            s -> {
                System.out.println(s);

                return s;
            });
        future.join();
        try {
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        // FutureUtils.combine(futures)
    }

    public CompletionStage getExceptionalStage() {
        CompletionStage<String> stage = CompletableFuture.completedFuture("thenAcceptAsync message").thenApply(s -> {
            return 0L / 1L + "";
        });
        return stage;
    }

    @Test
    public void testExcepion() {
        CompletionStage<String> stage = getExceptionalStage();
        stage.thenCompose(s -> {
            Long a = 0L / 0L;
            return CompletableFuture.completedFuture(s);
        }).exceptionally(e -> {
            System.out.println("exception");
            e.printStackTrace();
            return "";
        });
    }

    public static void main(String[] args) {
        try {
            thenAcceptExample();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
