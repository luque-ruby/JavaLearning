package concurrent.stream;

import java.util.Arrays;
import java.util.List;

/**
 * Created by luque_ruby on 2022/2/22.
 */

public class Stream {
    public static void main(String[] args) {
        Usr usr1 = new Usr(1, 21, "a");
        Usr usr2 = new Usr(2, 22, "b");
        Usr usr3 = new Usr(3, 23, "c");
        Usr usr4 = new Usr(4, 24, "d");
        Usr usr5 = new Usr(5, 25, "e");
        Usr usr6 = new Usr(6, 26, "f");
        Usr usr7 = new Usr(7, 27, "g");
        Usr usr8 = new Usr(8, 28, "h");

        List<Usr> usrList = Arrays.asList(usr1, usr2, usr3, usr4, usr5, usr6, usr7, usr8);

        /**
         * 1.获取id为偶数的
         * 2.年龄大于23的
         * 3.用户名转为大写字母
         * 4.用户名倒着排序
         * 5.只输出两个用户
         */
        usrList.stream()
                .filter(u -> u.getId() % 2 == 0)
                .filter(u -> u.getAge() > 23)
                .map(u -> u.getName().toUpperCase())
                .sorted((u1, u2) -> {return u2.compareTo(u1);})
                .limit(2)
                .forEach(System.out::println);
    }
}
