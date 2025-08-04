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
        //no commands,do nothing
        // TODO:May need to remove as base logic will do nothing
        if (cmdToExecute.length == 0) {
            System.out.println("No Command to execute");
            return;
        }

        String undoClassName = "UndoCommand";
        int undoableCount = 0;
        int undoCount = 0;

        for (int i=0; i<cmdToExecute.length; i++){

            //if UndoCommand and undoableCommand left, print error
            if(getSimpleClassName(cmdToExecute[i]).equals(undoClassName) && undoCount >= undoableCount) {
                System.out.println("No commands to undo");
                continue;
            }

            //if UndoCommand and there is more undoableCommand, run undoableCommand and increase undoCount
            if (getSimpleClassName(cmdToExecute[i]).equals(undoClassName)) {
                int[] undoableIndexArray = getArrayIndexOfUndoable(cmdToExecute,i,undoableCount);

                //Logic for undoing the right command.
                // E.g If 1 undo is already done, 2nd undoCommand would undo 2nd last undoableCommand
                int indexOfCommandToUndo = undoableIndexArray[(undoableIndexArray.length-1-(undoCount))];
                ((Undoable) cmdToExecute[indexOfCommandToUndo]).undo();
                history.push(cmdToExecute[i]);
                undoCount++;
                continue;
            }

            //After settling undoCommands, run normal commands
            //Keep track of UndoableCommands
            if (cmdToExecute[i] instanceof Undoable) {
                undoableCount++;
            }

            cmdToExecute[i].execute();
            history.push(cmdToExecute[i]);
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
