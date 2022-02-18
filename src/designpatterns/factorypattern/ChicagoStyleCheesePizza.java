package designpatterns.factorypattern;

/**
 * Created by luque_ruby on 2020/8/2.
 */
public class ChicagoStyleCheesePizza extends Pizza {
    public ChicagoStyleCheesePizza() {
        name = "chicago style deep dish cheese pizza";
        dough = "Extra thick crust dough";
        sauce = "plum tomato sauce";

        toppings.add("shredded mozzarella cheese");
    }

        void cut() {
        System.out.println("cutting the pizza into square slices");
    }
}

