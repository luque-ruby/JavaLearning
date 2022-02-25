package concurrent.lock.spinelock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by luque_ruby on 2022/2/25.
 * 自旋锁：
 *   public final int getAndAddInt(Object var1, long var2, int var4) {
 *     int var5;
 *     do {
 *          var5 = this.getIntVolatile(var1, var2);
 *     } while(!this.compareAndSwapInt(var1, var2, var5, var5 + var4));
 *
 *     return var5;
 *  }
 */
public class SpinLockTest {
    public static void main(String[] args) {
        SpinLock spinLock = new SpinLock();

        new Thread(()->{
            spinLock.myLock();
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                spinLock.myUnlock();
            }
        }, "T1").start();

        try {
            //加延迟，保证T1先获取到锁
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //T2进来时，是拿不到锁的，虽然会打印“T2==> mylock”，但是会等待T1执行完毕
        new Thread(()->{
            spinLock.myLock();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                spinLock.myUnlock();
            }
        }, "T2").start();
    }

}

