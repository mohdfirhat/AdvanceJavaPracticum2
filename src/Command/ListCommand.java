package Command;
import Receiver.Receiver;
import Exception.InvalidInputException;
/**
 * A child class for command of list type.
 * List Command requires no user inputs.
 */
public class ListCommand implements Command{
    /**
     * variable for receiver object to be passed into this command
     */
    private final Receiver receiver;
    /**
     * Constructor for the Delete class
     *
     * @param receiver Receiver object
     */
    public ListCommand(Receiver receiver) {
        this.receiver = receiver;
    }
    /**
     * The execute method is overriden to list the items
     * in the saved list.
     */
    @Override
    public void execute() throws InvalidInputException  {
        if (receiver.list.size() == 0) {
            throw new InvalidInputException("Invalid command as list is empty.");
        }
        System.out.println("List");
        receiver.list();
    }

    public void undo() {
    }
    /**
     * Undo command is not undoable.
     */
    @Override
    public boolean isUndoable() {
        return false;
    }
}
