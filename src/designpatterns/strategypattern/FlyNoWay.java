package designpatterns.strategypattern;

/**
 * Created by luque_ruby on 2020/7/28.
 */
public class FlyNoWay implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("I can't fly!");
    }
}
