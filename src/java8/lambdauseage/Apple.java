package java8.lambdauseage;

/**
 * Created by Administrator on 2019/5/17.
 */
public class Apple {
    private int weight;
    private String color;

    public Apple(String color, int weight) {
        this.weight = weight;
        this.color = color;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "lambdauseage.Apple{" +
                "weight=" + weight +
                ", color='" + color + '\'' +
                '}';
    }
}
