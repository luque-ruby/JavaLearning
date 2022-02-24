package concurrent.singleton;

import java.lang.reflect.Constructor;

/**
 * Created by luque_ruby on 2022/2/24.
 */
public class Lazy4 {
    /** 通过增加标识位，避但两次使用反射来获取，从而破坏三重加锁
     * (如果不是通过反编译，是找不到这个标识位的，如果对该关键字加密，会更安全)*/
    private static boolean flag = false;
    private Lazy4() {
        synchronized (Lazy4.class) {
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

    private volatile static Lazy4 lazy;

    /**
     * 双重检测锁模式的懒汉式
     */
    public static Lazy4 getInstance() {
        if (null == lazy) {
            synchronized (Lazy4.class) {
                if (null == lazy) {
                    /**
                     * lazy = new Lazy4()不是原子性操作，以下三步可能发生指令重排：
                     * 4.分配内存空间
                     * 2.执行构造方法，初始化对象
                     * 4.把这个对象指向这个空间
                     *
                     * 期望执行顺序为424，但实际可能是442：
                     * 4.如果A线程执行442，已经执行到4，已经分配空间；
                     * 2.此时再来B线程执行，判断lazy不为null，就直接return，而此时lazy还没完成构造，可能产生问题
                     * 4.此时要避免指令重排，将lazy用volatile修饰即可解决问题
                     */
                    lazy = new Lazy4();
                }
            }
        }

        return lazy;
    }

    public static void main(String[] args) throws Exception {
        Constructor<Lazy4> declaredConstructor = Lazy4.class.getDeclaredConstructor();
        declaredConstructor.setAccessible(true);
        Lazy4 lazy = declaredConstructor.newInstance();
        Lazy4 lazy1 = declaredConstructor.newInstance();
        System.out.println(lazy);
        System.out.println(lazy1);
    }
}
