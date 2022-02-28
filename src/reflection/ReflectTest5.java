package reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/** 通过反射，动态的创建对象*/
public class ReflectTest5 {
    public static void main(String[] args) throws Exception {
        /** 通过无参构造器创建对象*/
        System.out.println("通过无参构造器创建对象");
        Class c1 = Class.forName("reflection.User");
        User user1 = (User)c1.newInstance();//调用了无参构造器，如果没有无参构造器或者访问权限不够，就会报错
        System.out.println(user1);

        /** 通过有参构造器创建对象*/
        System.out.println("通过有参构造器创建对象");
        Constructor constructor = c1.getDeclaredConstructor(String.class, int.class, int.class);
        User user2 = (User)constructor.newInstance("Tom", 001, 21);
        System.out.println(user2);

        /** 通过调用普通方法*/
        System.out.println("通过调用普通方法");
        User user3 = (User)c1.newInstance();
        Method setName = c1.getDeclaredMethod("setName", String.class);
        setName.invoke(user3, "Jerry");
        System.out.println(user3);

        /** 通过反射操作属性*/
        System.out.println("通过反射操作属性");
        User user4 = (User)c1.newInstance();
        Field name = c1.getDeclaredField("name");
        name.setAccessible(true);//不能直接操作入有属性，需要通过setAccessible关闭程序的安全检查
        name.set(user4, "Ruby");
        System.out.println(user4.getName());
    }
}
