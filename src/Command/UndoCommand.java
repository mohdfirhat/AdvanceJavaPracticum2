package Command;

import java.util.Stack;
import Command.Undoable;

public class UndoCommand implements Command {


    public UndoCommand() {
    }
    @Override
    public void execute(Stack<Command> history) {
        if (!history.isEmpty()) {
            System.out.println("Undo");
            Command cmd = history.pop();
            cmd.undo();
        } else {
            System.out.println("Nothing to undo.");
        }
    }

    @Override
    public void undo() {

    }
}
