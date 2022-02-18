package java8.lambdauseage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.LongPredicate;
import java.util.function.Predicate;

/**
 * Created by luque_ruby on 2019/6/11.
 */
public class LambdaUsagePredict {
    public static void main(String[] args) {
        Runnable r1 = () -> System.out.println("hello1");
        Runnable r2 = new Runnable() {
            @Override
            public void run() {
                System.out.println("hello2");
            }
        };

//        process(r1);
//        process(r2);

        List<Apple> appleList = Arrays.asList(new Apple("green", 120), new Apple("red", 150));
        List<Apple> greenAppleList = filterByColor(appleList, apple -> apple.getColor().equals("green"));
//        System.out.println(greenAppleList);

        List<Apple> weightAppleList = filterByWeight(appleList, w -> w > 100);
//        System.out.println(weightAppleList);

        List<Apple> biPredict = filterByBiPredict(appleList, (s, w) -> s.equals("green") && w > 100);
        System.out.println(biPredict);
    }

    private static void process(Runnable r) {
        r.run();
    }

    private static List<Apple> filterByColor(List<Apple> apples, Predicate<Apple> predicate) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : apples) {
            if (predicate.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }

    private static List<Apple> filterByWeight(List<Apple> apples, LongPredicate weight) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : apples) {
            if (weight.test(apple.getWeight())) {
                result.add(apple);
            }
        }
        return result;
    }

    private static List<Apple> filterByBiPredict(List<Apple> apples, BiPredicate<String, Long> predicate) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : apples) {
            if (predicate.test(apple.getColor(), (long) apple.getWeight())) {
                result.add(apple);
            }
        }
        return result;
    }

}
