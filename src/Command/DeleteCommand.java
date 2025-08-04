package Command;
import Receiver.Receiver;

import java.util.List;

public class DeleteCommand implements Command , Undoable {
    private final Receiver receiver;
    private final int index;
    private String deletedItem;


    public DeleteCommand(Receiver receiver, int index) {
        this.receiver = receiver;
        this.index = index;
    }

    @Override
    public void execute() {
        List<String> items = receiver.getList();
        if (index >= 0 && index < items.size()) {
            deletedItem = items.get(index);
            receiver.delete(index);
            System.out.println("Delete");
        } else {
            System.out.println("Invalid index.");
        }

    }

    @Override
    public void undo() {
        receiver.list.add(index, deletedItem);
    }
}
