package Command;

import java.util.EmptyStackException;
import java.util.Stack;
import Exception.InvalidInputException;
import Receiver.Receiver;
/**
 * A child class for command of undo type.
 * Undo Command requires no user inputs.
 */
public class UndoCommand implements Command {
    /**
     * variable for the command history
     */
    Stack<Command> history;
    /**
     * Constructor for the Undo class
     *
     * @param receiver Receiver object
     * @param history Command history
     */
    public UndoCommand(Receiver receiver, Stack<Command> history) {
        this.history = history;
    }
    /**
     * The execute method is overriden to read the last command.
     * If there is a last command, it will call the undo method
     * in the command.
     */
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
    /**
     * Undo command is not undoable.
     */
    @Override
    public boolean isUndoable() {
        return false;
    }
}
