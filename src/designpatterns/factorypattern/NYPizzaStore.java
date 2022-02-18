package designpatterns.factorypattern;

/**
 * Created by luque_ruby on 2020/8/2.
 */
public class NYPizzaStore extends PizzaStore {
    @Override
    protected Pizza createPizza(String type) {
        if (type.equals("cheese")) {
            return new NYStyleCheesePizze();
        } else {
            return null;
        }
    }
}
