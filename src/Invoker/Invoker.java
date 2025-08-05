package Invoker;

import java.util.Stack;
import Command.Command;

public class Invoker {
    private Command[] cmdToExecute;

    public void setCmdToExecute(Command[] cmdToExecute) {
        this.cmdToExecute = cmdToExecute;
    }

    public void executeCommand(Stack<Command> history){
        for (Command command : cmdToExecute) {
            command.execute(history);
        }
    }
}
