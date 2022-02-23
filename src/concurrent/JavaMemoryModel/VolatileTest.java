package concurrent.JavaMemoryModel;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by luque_ruby on 2022/2/23.
 */
public class VolatileTest {
    private static volatile int num = 0;
    private static volatile AtomicInteger atomicInteger = new AtomicInteger();

    public static void add() {
        num++;//不是一个原子操作
    }

    public static void atomicAdd() {
        atomicInteger.getAndIncrement();//内部使用CAS
    }


    public static void main(String[] args) {
        /** 理论上应该变为20000
         * 但volatile只保证可见性，不保证原子性，而且num++不是原子操作，各线程均可进入
         * 如果将add方法用synchronized修饰，则可以保证原子性，也可以使用原子类解决原子性，性能消耗比synchronized低
         * */
        for (int i = 0; i <20; i++) {
            new Thread(()->{
                for (int j =0;j <1000;j++) {
                    add();
                }
            }).start();
        }

        while (Thread.activeCount() > 2) {//main gc两个线程
            Thread.yield();
        }

        System.out.println("num = " + num);

        /** 使用原子类*/
        for (int i = 0; i <20; i++) {
            new Thread(()->{
                for (int j =0;j <1000;j++) {
                    atomicAdd();
                }
            }).start();
        }

        while (Thread.activeCount() > 2) {//main gc两个线程
            Thread.yield();
        }

        System.out.println("atomicInteger = " + atomicInteger);
    }
}
