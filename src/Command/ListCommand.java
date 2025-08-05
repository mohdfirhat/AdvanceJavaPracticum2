package Command;
import Receiver.Receiver;

import java.util.Stack;
import Exception.InvalidInputException;

public class ListCommand implements Command{
    private final Receiver receiver;

    public ListCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() throws InvalidInputException  {
        System.out.println("List");
        receiver.list();
    }

    @Override
    public void undo() {
    }
    public boolean isUndoable() {
        return false;
    }
}
