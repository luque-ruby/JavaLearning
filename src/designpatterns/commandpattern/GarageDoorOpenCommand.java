package designpatterns.commandpattern;

/**
 * Created by luque_ruby on 2020/8/11.
 */
public class GarageDoorOpenCommand implements Command {
    Door door;

    public GarageDoorOpenCommand(Door door) {
        this.door = door;
    }

    @Override
    public void execute() {
        door.open();
    }
}
