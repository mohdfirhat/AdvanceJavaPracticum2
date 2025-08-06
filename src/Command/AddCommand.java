package Command;
import Receiver.Receiver;
import Exception.InvalidInputException;

public class AddCommand implements Command {
    private final Receiver receiver;
    private final String firstName, lastName, email;

    public AddCommand(Receiver receiver, String input){
        // Sanitize data
        input = input.trim();
        String [] inputArr = input.replaceAll("\\s+", " ").split(" ");
            //set variables
            this.receiver = receiver;
            this.firstName = titleCase(inputArr[0]);
            this.lastName = titleCase(inputArr[1]);
            this.email = inputArr[2];
    }

    @Override
    public void execute() throws InvalidInputException {

        // Validate data
        if (firstName.isBlank() || lastName.isBlank() || email.isBlank()) {
            throw new InvalidInputException("All three arguments are required to add.");
        }
        if (isInvalidEmail(email)) {
            throw new InvalidInputException("Invalid email address.");
        }

        String fullEntry = String.format("%s %s %s", firstName, lastName,
                email);
        //add() is item method in receiver to append new item to list
        receiver.add(fullEntry);
        System.out.println("add");
    }

    @Override
    public void undo() {
        receiver.delete(receiver.list.size() - 1);
    }

    @Override
    public boolean isUndoable() {
        return true;
    }


}
