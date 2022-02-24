package concurrent.singleton;

/**
 * Created by luque_ruby on 2022/2/24.
 */
public class StaticInnerClass {
    public static class InnerClass {
        private static final StaticInnerClass staticInnerClass = new StaticInnerClass();
    }

    private StaticInnerClass() {}

    public StaticInnerClass getInstance() {
        return InnerClass.staticInnerClass;
    }

    /** 使用静态内部类实现单例模式，但仍然不安全，反射可以破解*/
}
