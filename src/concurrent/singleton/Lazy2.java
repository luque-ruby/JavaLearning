package concurrent.singleton;

/**
 * Created by luque_ruby on 2022/2/24.
 */
public class Lazy2 {
    private Lazy2() {
        System.out.println(Thread.currentThread().getName() + "ok");
    }

    private volatile static Lazy2 lazy;

    /** 双重检测锁模式的懒汉式*/
    public static Lazy2 getInstance() {
        if (null == lazy) {
            synchronized (Lazy2.class) {
                if (null == lazy) {
                    /**
                     * lazy = new Lazy1()不是原子性操作，以下三步可能发生指令重排：
                     * 1.分配内存空间
                     * 2.执行构造方法，初始化对象
                     * 3.把这个对象指向这个空间
                     *
                     * 期望执行顺序为123，但实际可能是132：
                     * 1.如果A线程执行132，已经执行到3，已经分配空间；
                     * 2.此时再来B线程执行，判断lazy不为null，就直接return，而此时lazy还没完成构造，可能产生问题
                     * 3.此时要避免指令重排，将lazy用volatile修饰即可解决问题
                     */
                    lazy = new Lazy2();
                }
            }
        }

        return lazy;
    }

    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 10; i++) {
            new Thread(Lazy2::getInstance).start();
        }
    }
}
