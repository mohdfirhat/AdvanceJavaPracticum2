package Command;
import Receiver.Receiver;
import Exception.InvalidInputException;

/**
 * A child class for command of Add type.
 * Add Command is undoable, and requires 4 inputs (index, firstName, lastName and email)
 */
public class AddCommand implements Command {
    /**
     * variable for receiver object to be passed into this command
     */
    private final Receiver receiver;
    /**
     * variable for inputArr
     */
    private final String[] inputArr;

    /**
     * Constructor for the Add class
     *
     * @param receiver Receiver object
     * @param input input by user. Contains index, firstname, lastname and email.
     */
    public AddCommand(Receiver receiver, String input){
        input = input.trim();
        this.inputArr = input.replaceAll("\\s+", " ").split(" ");
            this.receiver = receiver;

    }

    /**
     * The execute method is overriden to add the user input
     * to the index specified. The index is saved for the undo command.
     */
    @Override
    public void execute() throws InvalidInputException {
        if (inputArr.length != 3) {
            throw new InvalidInputException("All three arguments are required to use add.");
        }
        String email;

        String firstName = titleCase(inputArr[0]);
        String lastName = titleCase(inputArr[1]);
        if (inputArr[2].contains("@")){
            email = inputArr[2];
        }else{
            email = titleCase(inputArr[2]);}

        // Validate data
        if (firstName.isBlank() || lastName.isBlank() || email.isBlank()) {
            throw new InvalidInputException("All three arguments are required to add.");
        }

        if (isInvalidData3(email)) {
            throw new InvalidInputException("Invalid email address.");
        }

        String fullEntry = String.format("%s %s %s", titleCase(firstName), titleCase(lastName),
                email);
        //add() is item method in receiver to append new item to list
        receiver.add(fullEntry);
        System.out.println("add");
    }
    /**
     * Undo command deletes the entry at the saved index.
     */
    @Override
    public void undo() {
        receiver.delete(receiver.list.size() - 1);
    }

    /**
     * Add is undoable.
     */
    @Override
    public boolean isUndoable() {
        return true;
    }


}
