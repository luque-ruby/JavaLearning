package designpatterns.commandpattern;

/**
 * Created by luque_ruby on 2020/8/11.
 */
public class LightOnCommand implements Command {
    Light light;

    public LightOnCommand(Light light) {
        this.light = light;
    }
    @Override
    public void execute() {
        light.on();
    }
}
