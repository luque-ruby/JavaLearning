package concurrent.function;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Created by luque_ruby on 2022/2/22.
 */
public class FunctionDemo {
    public static void main(String[] args) {
        /** 函数式接口*/
//        Function function = new Function<String, String>() {
//            @Override
//            public String apply(String s) {
//                return s + " append.";
//            }
//        };

        Function function = (s)->{return s + " append.";};

        System.out.println(function.apply("test"));

        /** 断定型接口，有输入参数，返回值只能是bool值*/
//        Predicate predicate = new Predicate<String>() {
//            @Override
//            public boolean test(String o) {
//                return o.isEmpty();
//            }
//        };

        Predicate<String> predicate = (s) -> {return s.isEmpty();};

        System.out.println(predicate.test("111"));

        /** 消费型接口，只有输入，没有返回值*/
        Consumer<String> consumer = (s)-> System.out.println("consumer " + s);
        consumer.accept("s");

        /** 供给型接口*/
        Supplier<String> supplier= ()->{return "supplier";};
        System.out.println(supplier.get());
    }
}
