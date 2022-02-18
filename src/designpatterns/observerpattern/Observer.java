package designpatterns.observerpattern;

/**
 * Created by luque_ruby on 2020/7/29.
 */
public interface Observer {
    public void update(float temp, float humidity, float pressure);
}
