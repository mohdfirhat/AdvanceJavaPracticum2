package Command;
import Receiver.Receiver;

import java.util.Stack;

public class ListCommand implements Command{
    private final Receiver receiver;

    public ListCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute(Stack<Command> history) {
        receiver.list();
        System.out.println("List");
    }

    @Override
    public void undo() {
    }
}
