package designpatterns.strategypattern;

/**
 * Created by luque_ruby on 2020/7/28.
 */
public class FlyWithWings implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("I'm flying!");
    }
}
