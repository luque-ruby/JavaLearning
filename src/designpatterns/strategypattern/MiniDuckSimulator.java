package designpatterns.strategypattern;

/**
 * Created by luque_ruby on 2020/7/28.
 */
public class MiniDuckSimulator {
    public static void main(String[] args) {
        Duck mallard = new MallardDuck();
        mallard.setFlyBehavior(new FlyWithWings());
        mallard.setQuackBehavior(new Quack());

        mallard.performQuack();
        mallard.performFly();

        Duck model = new ModelDuck();
        model.setQuackBehavior(new MuteQuack());
        model.setFlyBehavior(new FlyRockerPowered());
        model.performFly();
    }
}
