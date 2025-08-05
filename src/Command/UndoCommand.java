package Command;

import java.util.EmptyStackException;
import java.util.Stack;
import Command.Command;
import Exception.InvalidInputException;

public class UndoCommand implements Command {
    Stack<Command> history;


    public UndoCommand(Stack<Command> history) {
        this.history = history;
    }

    @Override
    public void execute() throws InvalidInputException {
        try {
            Command cmd = history.pop();
            System.out.println("Undo");
            cmd.undo();
        } catch (EmptyStackException e) {
            throw new InvalidInputException("Nothing to undo.");
        }
    }
    @Override
    public void undo() {

    }
    public boolean isUndoable() {
        return false;
    }
}
