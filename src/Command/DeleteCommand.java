package Command;
import Receiver.Receiver;
import Exception.InvalidInputException;
import java.util.List;
/**
 * A child class for command of delete type.
 * Add Command is undoable, and require only 1 input (index)
 */
public class DeleteCommand implements Command {
    /**
     * variable for receiver object to be passed into this command
     */
    private final Receiver receiver;
    /**
     * variable for input
     */
    private String input;
    /**
     * variable for saved for index
     */
    private int index;
    /**
     * variable for deletedItem in case user needs to undo
     */
    private String deletedItem;
    /**
     * variable for list of items at the point delete function is called
     */
    private List<String> items;
    /**
     * Constructor for the Delete class
     *
     * @param receiver Receiver object
     * @param input input by user. Contains index
     */
    public DeleteCommand(Receiver receiver, String input) {
        items = receiver.list;
        this.receiver = receiver;
        this.input = input;
    }
    /**
     * The execute method is overriden to delete the user input
     * to the index specified. The deleteditem is saved for the undo command.
     */
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
    /**
     * Undo command adds back the deletedItem at the saved index.
     */
    @Override
    public void undo() {
        receiver.add(this.index, deletedItem);
    }
    /**
     * Delete command is undoable.
     */
    @Override
    public boolean isUndoable() {
        return true;
    }
}
