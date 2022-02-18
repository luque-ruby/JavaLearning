package concurrent.producerconsumer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by luque_ruby on 2022/2/17.
 */
public class ProducerConsumer3 {

    public static void main(String[] args) {
        Data data = new Data();

        new Thread(() -> {
            for (int i = 0; i < 40; i++)
                try {
                    data.printA();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        }, "A").start();
        new Thread(() -> {
            for (int i = 0; i < 40; i++)
                try {
                    data.printB();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        }, "B").start();

        new Thread(() -> {
            for (int i = 0; i < 40; i++)
                try {
                    data.printC();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        }, "C").start();
    }

    //判断等待，业务，通知
    static class Data {
        private Lock lock = new ReentrantLock();

        /**
         * condition可以精准地通知和唤醒线程
         */
        private Condition condition1 = lock.newCondition();
        private Condition condition2 = lock.newCondition();
        private Condition condition3 = lock.newCondition();

        private int num = 1;

        public void printA() throws InterruptedException {
            System.out.println("printA");
            lock.lock();
            try {
                while (1 != num) {
                    condition1.await();
                }
                num = 2;
                System.out.println(Thread.currentThread().getName() + "=>AAA" + "--num = " + num);

                //唤醒指定的监视器
                condition2.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        public synchronized void printB() throws InterruptedException {
            System.out.println("printB");
            lock.lock();
            try {
                while (2 != num) {
                    condition2.await();
                }
                num = 3;
                System.out.println(Thread.currentThread().getName() + "=>BBB" + "--num = " + num);
                condition3.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        public synchronized void printC() throws InterruptedException {
            System.out.println("printC");
            lock.lock();
            try {
                while (3 != num) {
                    condition3.await();
                }
                num = 1;
                System.out.println(Thread.currentThread().getName() + "=>CCC" + "--num = " + num);
                condition1.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}
