package concurrent.lock;

import java.util.concurrent.TimeUnit;

/**
 * Created by luque_ruby on 2022/2/17.
 */
public class LockTest1 {
    public static void main(String[] args) {
        Phone1 phone = new Phone1();

        new Thread(phone::sendMsg, "A").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(phone::sendCall, "B").start();

        /** 先输出发短信，原因为synchronized锁的对象是方法的调用者，即两个方法用的是同一把锁(都是phone对象)
         *  谁先获得锁谁先执行*/
    }

}

class Phone1 {
    public synchronized void sendMsg() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("发短信");
    }

    public synchronized void sendCall() {
        System.out.println("打电话");
    }
}
