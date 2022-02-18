package concurrent.lock;

import java.util.concurrent.TimeUnit;

/**
 * Created by luque_ruby on 2022/2/17.
 */
public class LockTest2 {
    public static void main(String[] args) {
        Phone2 phone = new Phone2();

        new Thread(phone::sendMsg, "A").start();
        new Thread(phone::hello, "B").start();

        /** 先输出hello，原因为hello方法没有锁，不是同步方法，是普通方法不受锁的影响*/
    }

}
class Phone2 {
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

    public void hello() {
        System.out.println("hello");
    }
}
