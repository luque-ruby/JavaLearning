package designpatterns.commandpattern;

/**
 * Created by luque_ruby on 2020/8/11.
 */
public class RemoteControlTest {
    public static void main(String[] args) {
        SimpleRemoteControl remote = new SimpleRemoteControl();

        Command command;

        Light light = new Light();
        command = new LightOnCommand(light);
        remote.setCommand(command);
        remote.buttonWasPressed();

        Door door = new Door();
        command = new GarageDoorOpenCommand(door);
        remote.setCommand(command);
        remote.buttonWasPressed();
    }
}
