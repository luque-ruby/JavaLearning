package java8.lambdauseage;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.IntFunction;

/**
 * Created by luque_ruby on 2019/7/1.
 */
public class LambdaUseageFunction {
    public static void main(String[] args) {
        String result1 = testFunction(new Apple("yellow", 100), Apple::toString);
        System.out.println(result1);

        IntFunction<Double> f = i -> i * 100.0d;
        double result2 = f.apply(10);
        System.out.println(result2);

        Apple apple = testBiFunction("blue", 130, Apple::new);
        System.out.println(apple);
    }

    private static String testFunction(Apple apple, Function<Apple, String> fun) {
        return fun.apply(apple);
    }

private static Apple testBiFunction(String color, long weight, BiFunction<String, Integer, Apple> biFunction) {
        return biFunction.apply(color, (int) weight);
}


}
