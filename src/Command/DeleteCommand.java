package Command;
import Receiver.Receiver;
import Exception.InvalidInputException;
import java.util.List;

public class DeleteCommand implements Command {
    private final Receiver receiver;
    private String input;
    private int index;
    private String deletedItem;
    private List<String> items;

    public DeleteCommand(Receiver receiver, String input) {
        items = receiver.list;
        this.receiver = receiver;
        this.input = input;
    }

    @Override
    public void execute() throws InvalidInputException {
        try{
            int index = Integer.parseInt(input.trim())-1;
            if (index < 0 || index >= items.size()) {
                throw new  InvalidInputException("invalid index");
            }
            this.index = index;
            this.deletedItem = items.get(index);
            receiver.delete(index);
            System.out.println("Delete");
        } catch (NumberFormatException e) {
            //change error message
            throw new InvalidInputException("Unable to parse index. Invalid number format");
        }
    }

    @Override
    public void undo() {
        receiver.add(this.index, deletedItem);
    }

    @Override
    public boolean isUndoable() {
        return true;
    }
}
