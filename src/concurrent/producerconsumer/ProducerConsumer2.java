package concurrent.producerconsumer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by luque_ruby on 2022/2/17.
 */
public class ProducerConsumer2 {
    public static void main(String[] args) {
        Data data = new Data();

        new Thread(()->{for (int i = 0 ; i < 40; i++)
            try {
                data.producer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "A").start();
        new Thread(()->{for (int i = 0 ; i < 40; i++)
            try {
                data.consumer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "B").start();

        new Thread(()->{for (int i = 0 ; i < 40; i++)
            try {
                data.producer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "C").start();
        new Thread(()->{for (int i = 0 ; i < 40; i++)
            try {
                data.consumer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "D").start();
    }

    //判断等待，业务，通知
    static class Data {
        private int num = 0;
        Lock lock = new ReentrantLock();

        Condition condition = lock.newCondition();

        public void producer() throws InterruptedException {
            lock.lock();
            try {
                while (0 != num) {
                    condition.await();
                }

                num++;
                System.out.println(Thread.currentThread().getName() + "=>" + num);
                condition.signalAll();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        public synchronized void consumer() throws InterruptedException {
            lock.lock();
            try {
                while (0 == num) {
                    condition.await();
                }

                num--;
                System.out.println(Thread.currentThread().getName() + "=>" + num);
                condition.signalAll();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}
