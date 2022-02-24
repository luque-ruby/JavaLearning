package concurrent.singleton;

/**
 * Created by luque_ruby on 2022/2/24.
 */
public class Hungry {
    private Hungry() {
    }

    private final static Hungry hungry = new Hungry();

    public static Hungry getInstance() {
        return hungry;
    }
}
