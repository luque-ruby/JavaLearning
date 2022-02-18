package designpatterns.strategypattern;

/**
 * Created by luque_ruby on 2020/7/28.
 */
public class Quack implements QuackBehavior {
    @Override
    public void quack() {
        System.out.println("Quack");
    }
}
