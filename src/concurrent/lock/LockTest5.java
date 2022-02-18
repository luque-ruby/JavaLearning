package concurrent.lock;

import java.util.concurrent.TimeUnit;

/**
 * Created by luque_ruby on 2022/2/17.
 */
public class LockTest5 {
    public static void main(String[] args) {
        Phone5 phone1 = new Phone5();
        Phone5 phone2 = new Phone5();

        new Thread(()->{phone1.sendMsg();}, "A").start();
        new Thread(()->{phone2.sendCall();}, "B").start();

        /** 先输出发短信，原因为是静态方法，即类一加载就有了，锁的是Class(全局唯一)，所以二者锁相同*/
    }
}

class Phone5 {
    public static synchronized void sendMsg() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("发短信");
    }

    public static synchronized void sendCall() {
        System.out.println("打电话");
    }
}
