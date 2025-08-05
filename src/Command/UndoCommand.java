package Command;

import java.util.Stack;
import Command.Command;
import Exception.InvalidInputException;

public class UndoCommand implements Command {
    Stack<Command> history;


    public UndoCommand(Stack<Command> history) {
        this.history = history;
    }

    @Override
    public void execute() {
        try {
            if (history.isEmpty()) {
                throw new InvalidInputException("Nothing to undo.");
            }
        } catch (InvalidInputException e) {
            System.out.println(e.getMessage());
            return;
        }

        System.out.println("Undo");
        Command cmd = history.pop();
        cmd.undo();
    }
    @Override
    public void undo() {

    }
    public boolean isUndoable() {
        return false;
    }
}
