package concurrent.CAS;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * Created by luque_ruby on 2022/2/25.
 */
public class CASDemo3 {
    static AtomicStampedReference<Integer> atomicStampedInteger = new AtomicStampedReference<>(1, 1);

    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(2020);

        /** ABA问题：
         * 指在CAS操作时，其他线程将变量值A改为了B，但是又被改回了A，
         * 等到本线程使用期望值A与当前变量进行比较时，发现变量A没有变，
         * 于是CAS就将A值进行了交换操作，但是实际上该值已经被其他线程改变过。
         * */
        //捣乱的线程
//        System.out.println(atomicInteger.compareAndSet(2020, 2021));
//        System.out.println(atomicInteger.compareAndSet(2021, 2020));
//        System.out.println(atomicInteger);
//
//        //期望的线程(取的值是已被修改过的)
//        System.out.println(atomicInteger.compareAndSet(2020, 6666));
//        System.out.println(atomicInteger);

        /** 解决以上问题*/
        new Thread(()->{
            int stamp = atomicStampedInteger.getStamp();
            System.out.println("a1=>" + stamp);

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            atomicStampedInteger.compareAndSet(1, 2,
                    atomicStampedInteger.getStamp(), atomicStampedInteger.getStamp() + 1);
            System.out.println("a2=>" + atomicStampedInteger.getStamp());

            System.out.println(atomicStampedInteger.compareAndSet(2, 1,
                    atomicStampedInteger.getStamp(), atomicStampedInteger.getStamp() + 1));
            System.out.println("a3=>" + atomicStampedInteger.getStamp());
            }, "a").start();

        new Thread(()->{
            int stamp = atomicStampedInteger.getStamp();
            System.out.println("b1=>" + stamp);

            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(atomicStampedInteger.compareAndSet(1, 3, stamp, stamp + 1));
            System.out.println("b2=>" + stamp);
        }, "b").start();
    }
}
