package concurrent.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by luque_ruby on 2022/2/21.
 * 多线程、线程池一般会使用blocking queue
 */
public class BlockingQueueTest {
    public static void main(String[] args) {
        throwException();
        hasReturnValue();
        try {
            alwaysBlocking();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            timedBlocking();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 抛出异常
     */
    public static void throwException() {
        System.out.println("**********throwException**********");
        ArrayBlockingQueue blockingQueue = new ArrayBlockingQueue(3);
        blockingQueue.add("a");
        blockingQueue.add("b");
        blockingQueue.add("c");

        //取出队首元素
        System.out.println(blockingQueue.peek());
        System.out.println(blockingQueue.element());

        //再添加时就报错
//        blockingQueue.add("d");

        blockingQueue.remove();
        blockingQueue.remove();
        blockingQueue.remove();

        //再移除就报错
//        blockingQueue.remove();
    }

    /**
     * 不抛出异常，有返回值
     */
    public static void hasReturnValue() {
        System.out.println("**********hasReturnValue**********");
        ArrayBlockingQueue blockingQueue = new ArrayBlockingQueue(3);
        blockingQueue.offer("a");
        blockingQueue.offer("b");
        blockingQueue.offer("c");

        //再添加时就返回fable，但不抛出异常
        System.out.println(blockingQueue.offer("d"));

        blockingQueue.poll();
        blockingQueue.poll();
        blockingQueue.poll();

        //再移除就返回null，但不抛出异常
        System.out.println(blockingQueue.poll());
    }

    /**
     * 等待，阻塞(一直阻塞)
     */
    public static void alwaysBlocking() throws InterruptedException {
        System.out.println("**********alwaysBlocking**********");
        ArrayBlockingQueue blockingQueue = new ArrayBlockingQueue(3);
        blockingQueue.put("a");
        blockingQueue.put("b");
        blockingQueue.put("c");

        //队列已满，死等
//        blockingQueue.put("d");


        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());

        //队列已空，死等
//        System.out.println(blockingQueue.take());
    }

    /**
     * 等待，阻塞(等待超时)
     */
    public static void timedBlocking() throws InterruptedException {
        System.out.println("**********timedBlocking**********");
        ArrayBlockingQueue blockingQueue = new ArrayBlockingQueue(3);
        blockingQueue.offer("a");
        blockingQueue.offer("b");
        blockingQueue.offer("c");

        //等两秒钟
        blockingQueue.offer("d", 2, TimeUnit.SECONDS);


        blockingQueue.poll();
        blockingQueue.poll();
        blockingQueue.poll();
        blockingQueue.poll(2, TimeUnit.SECONDS);
    }
}

