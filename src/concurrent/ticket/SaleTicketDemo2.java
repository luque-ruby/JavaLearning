package concurrent.ticket;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by luque_ruby on 2022/2/16.
 */
public class SaleTicketDemo2 {
    public static void main(String[] args) {
        Ticket2 ticket2 = new Ticket2();

        new Thread(()->{for (int i = 0 ; i < 40; i++) ticket2.sale();}, "A").start();
        new Thread(()->{for (int i = 0 ; i < 40; i++) ticket2.sale();}, "B").start();
        new Thread(()->{for (int i = 0 ; i < 40; i++) ticket2.sale();}, "C").start();
    }
}

class Ticket2 {
    private int num = 30;

    private Lock lock = new ReentrantLock();

    public void sale() {
        lock.lock();
//        lock.tryLock()
        try {
            if (num > 0) {
                System.out.println(Thread.currentThread().getName() + "卖出了" + (num--) + "票，剩余: " + num);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
