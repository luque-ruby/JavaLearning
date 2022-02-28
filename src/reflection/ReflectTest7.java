package reflection;

import java.lang.annotation.*;
import java.lang.reflect.Field;

/** 反射获取注解*/
public class ReflectTest7 {
    public static void main(String[] args) throws Exception {
        Class c1 = Class.forName("reflection.User2");
        //通过反射获得注解
        Annotation[] annotations = c1.getAnnotations();
        for (Annotation annotation : annotations) {
            System.out.println("annotation" + annotation);
        }

        //获取注解的value的值
        Myannotation myannotation = (Myannotation)c1.getAnnotation(Myannotation.class);
        String value = myannotation.value();
        System.out.println(value);

        //获得类指定的注解
        Field field = c1.getDeclaredField("name");
        MyFidld myFidld = field.getAnnotation(MyFidld.class);
        System.out.println(myFidld.columnName());
        System.out.println(myFidld.type());
        System.out.println(myFidld.length());
    }
}

@Myannotation("db_student")
class User2 {
    @MyFidld(columnName = "db_id", type = "int", length = 10)
    private int id;
    @MyFidld(columnName = "db_age", type = "int", length = 10)
    private int age;
    @MyFidld(columnName = "db_name", type = "varchar", length = 10)
    private String name;

    public User2() {
    }

    public User2(int id, int age, String nae) {
        this.id = id;
        this.age = age;
        this.name = nae;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

//类名的注解
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface Myannotation {
    String value();
}

//属性的注解
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface  MyFidld {
    String columnName();
    String type();
    int length();
}