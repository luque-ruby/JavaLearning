package concurrent.lock;

import java.util.concurrent.TimeUnit;

/**
 * Created by luque_ruby on 2022/2/17.
 */
public class LockTest6 {
    public static void main(String[] args) {
        Phone6 phone1 = new Phone6();
        Phone6 phone2 = new Phone6();

        new Thread(()->{phone1.sendMsg();}, "A").start();
        new Thread(()->{phone2.sendCall();}, "B").start();

        /** 普通同步方法，锁的是new出来的具体对象，即this
         *  static同步方法，锁的是Class唯一的一个模板*/
        /** 先输出打电话，原因为打电话是普通同步方法，锁的是一个实例，而发短信方法锁的是整个Class，二者不是同一个锁*/
    }
}

class Phone6 {
    /** 静态同步方法*/
    public static synchronized void sendMsg() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("发短信");
    }

    /** 普通同步方法*/
    public synchronized void sendCall() {
        System.out.println("打电话");
    }
}
