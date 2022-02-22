package concurrent.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by luque_ruby on 2022/2/22.
 */
public class MyThreadPool {
    public static void main(String[] args) {
        ExecutorService executorService = new ThreadPoolExecutor(
                2,
                /** 最大线程数如何定义*/
                //1.CPU密集型，CPU有几核就是几，可以保持CPU效率最高
                //2.IO密集型(即程序中十分耗IO的线程)
                Runtime.getRuntime().availableProcessors(), //获取CPU核数
                3,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(3),
                Executors.defaultThreadFactory(),
                /** 四种拒绝策略*/
                // new ThreadPoolExecutor.AbortPolicy()); //队列满了，还有任务进来，不处理这个任务，抛出异常
                // new ThreadPoolExecutor.CallerRunsPolicy()); //谁提交的线程，由谁自己处理，当前就是由main线程处理，哪来的去哪里
                // new ThreadPoolExecutor.DiscardPolicy()); //队列满了，丢掉任务，不抛出异常
                new ThreadPoolExecutor.DiscardOldestPolicy()); //队列满了，深度和最早的竞争，不抛出异常


//        ThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler)

        try {
            for (int i = 0; i < 10; i++) {
                executorService.execute(() -> System.out.println(Thread.currentThread().getName() + " ok"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }
    }
}
