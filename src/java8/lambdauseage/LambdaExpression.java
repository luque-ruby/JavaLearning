package java8.lambdauseage;

import java.util.Comparator;

/**
 * Created by Administrator on 2019/5/17.
 */
public class LambdaExpression {
    Comparator<Apple> byColor = new Comparator<Apple>() {
        @Override
        public int compare(Apple o1, Apple o2) {
            return o1.getColor().compareTo(o2.getColor());
        }
    };

}
