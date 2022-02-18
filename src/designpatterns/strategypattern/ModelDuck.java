package designpatterns.strategypattern;

/**
 * Created by luque_ruby on 2020/7/28.
 */
public class ModelDuck extends Duck {
    private FlyBehavior flyBehavior;
    public QuackBehavior quackBehavior;

    public ModelDuck() {
        this.flyBehavior = new FlyNoWay();
        this.quackBehavior = new Quack();
    }
    @Override
    public void display() {
        System.out.println("I'm a model duck!");
    }
}
