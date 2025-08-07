package Invoker;

import java.util.Stack;
import Command.Command;
import Exception.InvalidInputException;

/**
 * Invoker Class is used to run the array of {@link Command}(s) given from the {@code Client}
 * It adds the commands that is successfully run and adds to the {@code history} stack if it is undoable.
 */
public class Invoker {
    /** variable for an array of {@link Command} for the invoker to execute*/
    private Command[] cmdToExecute;

    /** setter to set the array of {@link Command} {@code cmdToExecute} for the invoker to execute*/
    public void setCommandsForExecution(Command[] cmdToExecute) {
        this.cmdToExecute = cmdToExecute;
    }

    /**
     * run the array of {@link Command}(s) given from the {@code Client}
     * It adds the commands that is successfully run and adds to the {@code history} stack if it is undoable.
     * @param history a Stack of Commands which stores the previous successful undoable commands
     */
    public void executeCommand(Stack<Command> history){
        for (Command command : cmdToExecute) {
            try {
                command.execute();
                if (command.isUndoable()) {
                    history.push(command);
                }
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
