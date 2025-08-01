package Command;

import java.util.Stack;

public class UndoCommand implements Command {
    private Stack<Command> history;

    public UndoCommand(Stack<Command> history) {
        this.history = history;
    }
    @Override
    public void execute() {
        if (!history.isEmpty()) {
            Command cmd = history.pop();
            cmd.undo();
        } else {
            System.out.println("Nothing to undo.");
        }
    }

    @Override
    public void undo() {}
}
