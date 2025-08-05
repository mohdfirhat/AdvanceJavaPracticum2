package Invoker;

import java.util.Stack;
import Command.Command;
import Exception.InvalidInputException;

public class Invoker {
    private Command[] cmdToExecute;

    public void setCmdToExecute(Command[] cmdToExecute) {
        this.cmdToExecute = cmdToExecute;
    }

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
