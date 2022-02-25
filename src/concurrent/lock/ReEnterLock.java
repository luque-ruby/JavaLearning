package concurrent.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by luque_ruby on 2022/2/25.
 * 可重入锁：拿到了外面的锁之后，就可以拿到里面的锁，自动获得
 */
public class ReEnterLock {
    public static void main(String[] args) {
        /** synchronized版*/
        Phone phone = new Phone();

        new Thread(phone::sendMsg, "a").start();//执行完sendMsg方法后，由于sendMsg调用了call方法，
        // 所以继续执行call，然后才能b线程调用
        new Thread(phone::sendMsg, "b").start();

        Phone_ phone_ = new Phone_();
        new Thread(phone_::sendMsg, "c").start();
        new Thread(phone_::sendMsg, "d").start();


    }
}

class Phone {
    public synchronized void sendMsg() {
        System.out.println(Thread.currentThread().getName() + ": send msg.");
        call();//这里也有锁
    }

    public synchronized void call() {
        System.out.println(Thread.currentThread().getName() + ": call.");
    }
}

class Phone_ {
    Lock lock = new ReentrantLock();


    public void sendMsg() {
        lock.lock();//第一个锁

        /** lock必须配对，否则会死在里面，如果再加个锁，程序会死锁*/
        lock.lock();

        try {
            System.out.println(Thread.currentThread().getName() + ": send msg.");
            call();//第二个锁
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
            lock.unlock();/** 必须配对再加个unlock*/
        }
    }

    public void call() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + ": call.");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
