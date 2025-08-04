package Invoker;

import java.util.Stack;
import Command.Command;
import Command.Undoable;

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

    //Helper Method
    private String getSimpleClassName(Command cmd) {
        return cmd.getClass().getSimpleName();
    }

    private int[]  getArrayIndexOfUndoable(Command[] cmd,int currentIndex, int undoableCount) {
        int[] indexArray = new int[undoableCount];
        int arrayIndex = 0;

        //check command array from start to currentIndex for Undoable
        for (int i=0; i<currentIndex; i++){
            if (cmd[i] instanceof Undoable) {
                indexArray[arrayIndex++]=i;
            }
            if (arrayIndex==undoableCount-1){
                break;
            }
        }
        return indexArray;
    }
}
