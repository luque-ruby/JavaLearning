package concurrent.lock;

import java.util.concurrent.TimeUnit;

/**
 * Created by luque_ruby on 2022/2/17.
 */
public class LockTest3 {
    public static void main(String[] args) {
        Phone3 phone1 = new Phone3();
        Phone3 phone2 = new Phone3();

        new Thread(phone1::sendMsg, "A").start();
        new Thread(phone2::sendCall, "B").start();

        /** 先输出打电话，原因为是两个对象，即锁的对象也有两个，相互不相干，由于发短信前sleep了2秒，所以先输出打电话*/
    }

}
class Phone3 {
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
