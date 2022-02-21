package concurrent.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by luque_ruby on 2022/2/21.
 * 降低资源的消耗、提高影响速度、方便管理(线程利用、可以控制最大并发数、管理线程)
 * 线程池三大方法：单个线程的线程池、多个线程的线程池、不限数量的线程池
 *
 */
public class ThreadPool1 {
    public static void main(String[] args) {
        singlePool();
        numberedPools();
        unNumberedPools();
    }

    private static void singlePool() {
        System.out.println("*****single pool*****");
        ExecutorService singlePool = Executors.newSingleThreadExecutor();//单个线程
        try {
            for (int i = 0; i < 10; i++) {
                singlePool.execute((()-> System.out.println(Thread.currentThread().getName() + " ok")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            singlePool.shutdown();
        }
    }

    private static void numberedPools() {
        System.out.println("*****numbered pools*****");
        ExecutorService numberedPools = Executors.newFixedThreadPool(5);//固定大小的线程
        try {
            for (int i = 0; i < 10; i++) {
                numberedPools.execute((()->{
                    System.out.println(Thread.currentThread().getName() + " ok");
                }));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            numberedPools.shutdown();
        }
    }

    private static void unNumberedPools() {
        System.out.println("*****unnumbered pools*****");
        ExecutorService unNumberedPools = Executors.newCachedThreadPool();//可伸缩的
        try {
            for (int i = 0; i < 100; i++) {
                unNumberedPools.execute((()->{
                    System.out.println(Thread.currentThread().getName() + " ok");
                }));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            unNumberedPools.shutdown();
        }
    }
}
