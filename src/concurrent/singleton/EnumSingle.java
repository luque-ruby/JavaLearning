package concurrent.singleton;

import java.lang.reflect.Constructor;

/**
 * Created by luque_ruby on 2022/2/24.
 */
public enum EnumSingle {
    INSTANCE;

    private EnumSingle() {}

    public EnumSingle getInstance() {
        return INSTANCE;
    }
}

class Test {
    public static void main(String[] args) throws Exception {
        /** 二者是相同的*/
        EnumSingle enumSingle = EnumSingle.INSTANCE;
        EnumSingle enumSingle1 = EnumSingle.INSTANCE;

        System.out.println(enumSingle);
        System.out.println(enumSingle1);

        /** 尝试使用反射破坏，但报错是java.lang.NoSuchMethodException: concurrent.singleton.EnumSingle.<init>()
         * 与预期不符*/
//        Constructor<EnumSingle> declaredConstructor = EnumSingle.class.getDeclaredConstructor(null);
//        declaredConstructor.setAccessible(true);
//        EnumSingle enumSingle2 = declaredConstructor.newInstance();
//        System.out.println(enumSingle2);

        /** 反编译后，发现是有参构造，此时才会报错：Cannot reflectively create enum objects*/
        Constructor<EnumSingle> declaredConstructor = EnumSingle.class.getDeclaredConstructor(String.class, int.class);
        declaredConstructor.setAccessible(true);
        EnumSingle enumSingle2 = declaredConstructor.newInstance();
        System.out.println(enumSingle2);
    }
}
