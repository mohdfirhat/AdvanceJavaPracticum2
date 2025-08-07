package Command;
import Receiver.Receiver;
import Exception.InvalidInputException;
import java.util.List;

/**
 * A child class for command of update type.
 * Update Command requires mandatory user input of index, and at least
 * one of (firstName, lastName, email).
 */
public class UpdateCommand implements Command {
    /**
     * variable for receiver object to be passed into this command
     */
    private Receiver receiver;
    /**
     * variable for index at which line is updated
     */
    private int index;
    /**
     * variable for the original input which is replaced
     */
    private String originalEntry;
    /**
     * variable user input stored in an array
     */
    private String[] inputArr;
    /**
     * variable for items list at the point update command is called
     */
    private List<String> items;
    /**
     * variable for the updatedEntry object
     */
    private String updatedEntry;
    /**
     * Constructor for the update class
     *
     * @param receiver Receiver object
     * @param input user input
     */
    public UpdateCommand(Receiver receiver, String input) {
        items = receiver.list;
        this.receiver = receiver;
        this.inputArr = input.trim().replaceAll("\\s+", " ").split(" ");
    }
    /**
     * The execute method is overriden to read the update the
     * required line.
     */
    @Override
    public void execute() throws InvalidInputException {
        try {
            this.index = Integer.parseInt(inputArr[0].trim()) - 1;
            if (this.index < 0 || this.index >= items.size()) {
                throw new InvalidInputException("invalid index");
            }

            if (inputArr.length > 4 || inputArr.length <= 1) {
                throw new InvalidInputException("invalid number of inputs given");
            }

            if (inputArr.length == 4) {
                if (isInvalidData3(inputArr[3])) {
                    throw new InvalidInputException("Invalid email address.");
                }
            }
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Invalid number format");
        }


        this.originalEntry = receiver.list.get(this.index);
        String[] parts = originalEntry.split(" ", 3);
        String origFirstName = parts[0];
        String origLastName = parts[1];
        String origEmail = parts[2];
        String newEmail;

        if (inputArr.length == 4) {
            String newFirstName = titleCase(inputArr[1]);
            String newLastName = titleCase(inputArr[2]);

            if (inputArr[3].contains("@")){
                newEmail = inputArr[3];
            }else{
                newEmail = titleCase(inputArr[3]);}
            this.updatedEntry = newFirstName + " " + newLastName + " " + newEmail;
        } else if (inputArr.length == 3){
            String newFirstName = titleCase(inputArr[1]);
            String newLastName = titleCase(inputArr[2]);
            this.updatedEntry = newFirstName + " " + newLastName + " " + origEmail;
        }else if (inputArr.length == 2){
            String newFirstName = titleCase(inputArr[1]);
            this.updatedEntry = newFirstName + " " + origLastName + " " + origEmail;
        }

        receiver.update(index, updatedEntry);
        System.out.println("update");
    }

    @Override
    public void undo() {
        receiver.update(index, originalEntry);
    }
    /**
     * Update command is undoable.
     * @return true
     */
    @Override
    public boolean isUndoable() {
        return true;
    }
}
