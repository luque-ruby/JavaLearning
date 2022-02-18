package concurrent.ticket;

/**
 * Created by luque_ruby on 2022/2/16.
 */
public class SaleTicketDemo1 {
    public static void main(String[] args) {
        Ticket1 ticket1 = new Ticket1();

        new Thread(()->{for (int i = 0 ; i < 40; i++) ticket1.sale();}, "A").start();
        new Thread(()->{for (int i = 0 ; i < 40; i++) ticket1.sale();}, "B").start();
        new Thread(()->{for (int i = 0 ; i < 40; i++) ticket1.sale();}, "C").start();
    }
}

class Ticket1 {
    private int num = 30;

    public synchronized void sale() {
        if (num > 0) {
            System.out.println(Thread.currentThread().getName() + "卖出了" + (num--) + "票，剩余: " + num);
        }
    }
}