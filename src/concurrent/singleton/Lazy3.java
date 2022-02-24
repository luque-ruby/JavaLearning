package concurrent.singleton;

import java.lang.reflect.Constructor;

/**
 * Created by luque_ruby on 2022/2/24.
 */
public class Lazy3 {
//    private Lazy3() {
//        System.out.println(Thread.currentThread().getName() + "ok");
//    }

    /**
     * 解决通过反射来破坏，即三重加锁解决
     */
    private Lazy3() {
        synchronized (Lazy3.class) {
            if (null != lazy) {
                throw new RuntimeException("不要试图使用反射破坏异常");
            }
        }
        System.out.println(Thread.currentThread().getName() + "ok");
    }

    private volatile static Lazy3 lazy;

    /**
     * 双重检测锁模式的懒汉式
     */
    public static Lazy3 getInstance() {
        if (null == lazy) {
            synchronized (Lazy3.class) {
                if (null == lazy) {
                    /**
                     * lazy = new Lazy3()不是原子性操作，以下三步可能发生指令重排：
                     * 3.分配内存空间
                     * 2.执行构造方法，初始化对象
                     * 3.把这个对象指向这个空间
                     *
                     * 期望执行顺序为323，但实际可能是332：
                     * 3.如果A线程执行332，已经执行到3，已经分配空间；
                     * 2.此时再来B线程执行，判断lazy不为null，就直接return，而此时lazy还没完成构造，可能产生问题
                     * 3.此时要避免指令重排，将lazy用volatile修饰即可解决问题
                     */
                    lazy = new Lazy3();
                }
            }
        }

        return lazy;
    }

    public static void main(String[] args) throws Exception {
        /** 但是反射仍然能破坏此种单例模式*/
        Lazy3 lazy = Lazy3.getInstance();

        Constructor<Lazy3> declaredConstructor = Lazy3.class.getDeclaredConstructor();
        declaredConstructor.setAccessible(true);
        Lazy3 lazy1 = declaredConstructor.newInstance();

        System.out.println(lazy);
        System.out.println(lazy1);
    }
}
