package reflection;

import java.util.Arrays;

public class ReflectTest1 {
    public static void main(String[] args) {
        //通过反射获取类的class对象
        try {
            Class c1 = Class.forName("reflection.User");
            Class c2 = Class.forName("reflection.User");

            //一个类在内在中只有一个class对象
            //一个类被加载后，类的整个结构都会被封闭在class对象中
            System.out.println(c1.hashCode());//c1和c2是相同的
            System.out.println(c2.hashCode());

            System.out.println(Arrays.toString(c1.getFields()));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

class User {
    private String name;
    private int id;
    private int age;

    public User() {
    }

    public User(String name, int id, int age) {
        this.name = name;
        this.id = id;
        this.age = age;
    }

    private User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    private void test() {

    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", age=" + age +
                '}';
    }
}