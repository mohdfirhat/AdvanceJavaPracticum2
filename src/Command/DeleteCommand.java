package Command;
import Receiver.Receiver;
import Exception.InvalidInputException;

import java.util.List;
import java.util.Stack;

public class DeleteCommand implements Command , Undoable {
    private final Receiver receiver;
    private final int index;
    private String deletedItem;
    private List<String> items;

    public DeleteCommand(Receiver receiver, int index) {
        items = receiver.list;
        if (index < 0 || index >= items.size()) {
            throw new  InvalidInputException("invalid index");
        }
        this.receiver = receiver;
        this.index = index-1;
    }

    @Override
    public void execute(Stack<Command> history) {
        deletedItem = items.get(index);
        receiver.delete(index);
        history.push(this);
        System.out.println("Delete");
    }

    @Override
    public void undo() {
        receiver.list.add(index, deletedItem);
    }
}
