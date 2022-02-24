package concurrent.singleton;

/**
 * Created by luque_ruby on 2022/2/24.
 */
public class Lazy1 {
    private Lazy1() {
        System.out.println(Thread.currentThread().getName() + "ok");
    }

    private static Lazy1 lazy;


    /** 单线程没问题，多线程有问题*/
    public static Lazy1 getInstance() {
        if (null == lazy) {
            lazy = new Lazy1();
        }

        return lazy;
    }

    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 10; i++) {
            new Thread(Lazy1::getInstance).start();
        }
    }
}
