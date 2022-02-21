package concurrent.queue;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by luque_ruby on 2022/2/21.
 * 同步队列，进去一个元素，必须等待取出来之后，才能再放入一个元素
 */
public class SynchronousQueueTest {
    public static void main(String[] args) {
        SynchronousQueue<String> synchronousQueue = new SynchronousQueue<>();

        new Thread(()->{
            try {
                synchronousQueue.put("1");
                System.out.println(Thread.currentThread().getName() + ": put 1.");
                synchronousQueue.put("2");
                System.out.println(Thread.currentThread().getName() + ": put 2.");
                synchronousQueue.put("3");
                System.out.println(Thread.currentThread().getName() + ": put 3 .");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "T1").start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName() + ": take" + synchronousQueue.take());
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName() + ": take" + synchronousQueue.take());
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName() + ": take" + synchronousQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "T2").start();
    }
}
