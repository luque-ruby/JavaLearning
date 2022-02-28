package reflection;

import java.util.Arrays;

public class ReflectTest4 {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException, NoSuchMethodException {
        Class c1 = Class.forName("reflection.User");

        /** 获得类名*/
        System.out.println("获得类名");
        System.out.println(c1.getName()); //获得包名+类名
        System.out.println(c1.getSimpleName());//获得类名

        /** 获得类的属性*/
        System.out.println("获得类的属性");
        System.out.println(Arrays.toString(c1.getFields()));//只能找到public属性
        System.out.println(Arrays.toString(c1.getDeclaredFields()));//可以找到所有属性
        //System.out.println(c1.getField("name"));//获得指定public属性的值
        System.out.println(c1.getDeclaredField("name"));//获得属性的值

        /** 获得类的方法*/
        System.out.println("获得类的方法");
        System.out.println(Arrays.toString(c1.getMethods()));//获得本类及其父类全部public方法
        System.out.println(Arrays.toString(c1.getDeclaredMethods()));//获得本类全部public方法
        System.out.println(c1.getMethod("getName", null));//获得指定的方法
        System.out.println(c1.getMethod("setName", String.class));//获得指定的方法

        /** 获得指定的构造器*/
        System.out.println("获得指定的构造器");
        System.out.println(Arrays.toString(c1.getConstructors()));//获得本类全部public构造方法
        System.out.println(Arrays.toString(c1.getDeclaredConstructors()));////获得本类全部构造方法
        System.out.println(c1.getDeclaredConstructor(String.class, int.class));
    }
}
