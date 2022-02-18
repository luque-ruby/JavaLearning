package java8.lambdauseage;

import java.util.function.Supplier;

/**
 * Created by luque_ruby on 2019/7/1.
 */
public class LambdaUseageSupplier {
    public static void main(String[] args) {
        Supplier<String> s = String::new;
        System.out.println(s.get().getClass());

        Apple apple = createApple(() -> new Apple("green", 100));
        System.out.println(apple);
    }

    private static Apple createApple(Supplier<Apple> supplier) {
        return supplier.get();
    }
}
