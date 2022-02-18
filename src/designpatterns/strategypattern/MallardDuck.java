package designpatterns.strategypattern;

/**
 * Created by luque_ruby on 2020/7/28.
 */
public class MallardDuck extends Duck {
    private QuackBehavior quackBehavior;

    private FlyBehavior flyBehavior;
    public MallardDuck() {
        quackBehavior = new Quack();
        flyBehavior = new FlyWithWings();
    }

    @Override
    public void display() {
        System.out.println("I'm a real Mallard duck");
    }
}
