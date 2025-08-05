package Command;

import java.util.Stack;
import Command.Command;
import Exception.InvalidInputException;

public class UndoCommand implements Command {


    public UndoCommand() {
    }

    @Override
    public void execute(Stack<Command> history) {
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
}
