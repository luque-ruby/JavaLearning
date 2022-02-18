package java8.lambdauseage;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * Created by luque_ruby on 2019/6/11.
 */
public class LambdaUsageConsumer {
    public static void main(String[] args) {
        List<Apple> appleList = Arrays.asList(new Apple("green", 120), new Apple("red", 150));

//        simpleTestConsumer(appleList, System.out::println);

        simpleBiConsumer(appleList, "XXX: ", (a, s) -> System.out.println(s + a.getColor() + "weight >= " + a.getWeight()));
    }

    private static void simpleTestConsumer(List<Apple> apples, Consumer<Apple> consumer) {
        for (Apple apple : apples) {
            consumer.accept(apple);
        }
    }

    private static void simpleBiConsumer(List<Apple> apples, String color, BiConsumer<Apple, String> consumer) {
        for (Apple apple : apples) {
            consumer.accept(apple, color);
        }
    }}
