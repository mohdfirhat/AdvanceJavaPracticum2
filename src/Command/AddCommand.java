package Command;
import Receiver.Receiver;
import Exception.InvalidInputException;

public class AddCommand implements Command {
    private final Receiver receiver;
    private final String[] inputArr;

    public AddCommand(Receiver receiver, String input){
        // Sanitize data
        input = input.trim();
        this.inputArr = input.replaceAll("\\s+", " ").split(" ");
            this.receiver = receiver;

    }

    @Override
    public void execute() throws InvalidInputException {
        if (inputArr.length != 3) {
            throw new InvalidInputException("All three arguments are required to use add.");
        }

        String firstName = inputArr[0];
        String lastName = inputArr[1];
        String email = inputArr[2];

        // Validate data
        if (firstName.isBlank() || lastName.isBlank() || email.isBlank()) {
            throw new InvalidInputException("All three arguments are required to add.");
        }
        if (!isTitleCase(firstName)) {
            throw new InvalidInputException("First Name must be in title case" +
                    ".");
        }
        if (!isTitleCase(lastName)) {
            throw new InvalidInputException("Last Name must be in title case" +
                    ".");
        }

        if (isInvalidData3(email)) {
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
