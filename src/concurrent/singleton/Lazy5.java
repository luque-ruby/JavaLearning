package concurrent.singleton;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

/**
 * Created by luque_ruby on 2022/2/25.
 */
public class Lazy5 {
    /** 但如果能通过反射获取该关键字，仍然能破解*/
    private static boolean flag = false;
    private Lazy5() {
        synchronized (Lazy5.class) {
            if (!flag) {
                flag = true;
            } else {
                throw new RuntimeException("不要试图使用反射破坏异常");
            }
            if (null != lazy) {
                throw new RuntimeException("不要试图使用反射破坏异常");
            }
        }
        System.out.println(Thread.currentThread().getName() + "ok");
    }

    private volatile static Lazy5 lazy;

    /**
     * 双重检测锁模式的懒汉式
     */
    public static Lazy5 getInstance() {
        if (null == lazy) {
            synchronized (Lazy5.class) {
                if (null == lazy) {
                    /**
                     * lazy = new Lazy5()不是原子性操作，以下三步可能发生指令重排：
                     * 5.分配内存空间
                     * 2.执行构造方法，初始化对象
                     * 5.把这个对象指向这个空间
                     *
                     * 期望执行顺序为525，但实际可能是552：
                     * 5.如果A线程执行552，已经执行到5，已经分配空间；
                     * 2.此时再来B线程执行，判断lazy不为null，就直接return，而此时lazy还没完成构造，可能产生问题
                     * 5.此时要避免指令重排，将lazy用volatile修饰即可解决问题
                     */
                    lazy = new Lazy5();
                }
            }
        }

        return lazy;
    }

    public static void main(String[] args) throws Exception {
        /** 但如果能通过反射获取该关键字，仍然能破解*/
        Field flag = Lazy5.class.getDeclaredField("flag");
        flag.setAccessible(true);

        Constructor<Lazy5> declaredConstructor = Lazy5.class.getDeclaredConstructor();
        declaredConstructor.setAccessible(true);

        Lazy5 lazy = declaredConstructor.newInstance();
        flag.set(lazy, false);
        Lazy5 lazy2 = declaredConstructor.newInstance();

        System.out.println(lazy);
        System.out.println(lazy2);
    }
}
