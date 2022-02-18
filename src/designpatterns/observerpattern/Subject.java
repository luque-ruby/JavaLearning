package designpatterns.observerpattern;

/**
 * Created by luque_ruby on 2020/7/29.
 */
public interface Subject {
    public void registerObserver(Observer o);
    public void removeObserver(Observer o);
    public void notifyObserver();
}
