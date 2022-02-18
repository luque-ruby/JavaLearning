package designpatterns.factorypattern;

/**
 * Created by luque_ruby on 2020/8/2.
 */
public class NYStyleCheesePizze extends Pizza {
    public NYStyleCheesePizze() {
        name = "NY Style Sauce and Cheese Pizza";
        dough = "Thin Crust Dough";
        sauce = "Marinara Sauce";

        toppings.add("Grated Reggiano Cheese");
    }
}
