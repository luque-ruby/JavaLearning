package concurrent.CAS;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by luque_ruby on 2022/2/24.
 * CAS:compare and set
 */
public class CASDemo {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(2020);

        /** public final boolean compareAndSet(int expect, int update)
         * 期望值是expect，并更新为update，如果与期望值不一致，则不更新
         * */

        //第一次能修改成功
        System.out.println(atomicInteger.compareAndSet(2020, 2021));
        System.out.println(atomicInteger);

        //第二次修改失败
        System.out.println(atomicInteger.compareAndSet(2020, 2021));
        System.out.println(atomicInteger);

        /** Jave无法操作内存，Java可以调用C++的native方法，而C++可以操作内存
         * 即native是Java的后门
         * */

        /** 自旋锁*/
//        public final int getAndAddInt(Object var1, long var2, int var4) {
//            int var5;
//            do {
//                var5 = this.getIntVolatile(var1, var2);
//            } while(!this.compareAndSwapInt(var1, var2, var5, var5 + var4));
//
//            return var5;
//        }

        /** CAS:
         * 比较当前工作内存中的值和主内存中的值，如果这个值是期望的，则执行操作，如果不是就一直循环
         * 缺点：1.由于底层一直循环，所以循环会耗时；2.一次性只能保证一个共享变量的原子性*/
    }


}
