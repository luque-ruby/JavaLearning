package designpatterns.strategypattern;

/**
 * Created by luque_ruby on 2020/7/28.
 */
public class FlyRockerPowered implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("I'm flying with a rocket!");
    }
}
