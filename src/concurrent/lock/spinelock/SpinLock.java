package concurrent.lock.spinelock;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by luque_ruby on 2022/2/25.
 */
class SpinLock {
    //Thread默认为null
    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    //加锁
    public void myLock() {
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName() + "==> mylock");

        //自旋锁
        while (!atomicReference.compareAndSet(null, thread)) {

        }
    }

    //解锁
    public void myUnlock() {
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName() + "==> myUnlock");

        atomicReference.compareAndSet(thread, null);
    }
}
