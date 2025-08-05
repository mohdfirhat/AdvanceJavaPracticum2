package Command;
import Receiver.Receiver;
import Exception.InvalidInputException;

import java.util.List;
import java.util.Stack;

public class DeleteCommand implements Command {
    private final Receiver receiver;
    private String input;
    private String deletedItem;
    private List<String> items;

    public DeleteCommand(Receiver receiver, String input) {
        items = receiver.list;
        this.receiver = receiver;
        this.input = input;
    }

    @Override
    public void execute() {
        try{
            if (Integer.parseInt(input.trim())-1 < 0 || Integer.parseInt(input.trim())-1 >= items.size()) {
                throw new  InvalidInputException("invalid index");
            }
        } catch (InvalidInputException e) {
            System.out.println(e.getMessage());
            return;
        } catch (NumberFormatException e) {
            //change error message
            System.out.println(e.getMessage());
            return;
        }

        int index = Integer.parseInt(input.trim())-1;
        this.deletedItem = items.get(index);
        receiver.delete(index);
        System.out.println("Delete");
    }

    @Override
    public void undo() {
        receiver.list.add(Integer.parseInt(input.trim())-1, deletedItem);
    }

    @Override
    public boolean isUndoable() {
        return true;
    }
}
