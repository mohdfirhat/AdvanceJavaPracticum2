package Command;
import Receiver.Receiver;
import Exception.InvalidInputException;

import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddCommand implements Command , Undoable {
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
    public void execute(Stack<Command> history) {

        // Validate data
        try {
        if (firstName.isBlank() || lastName.isBlank() || email.isBlank()) {
            throw new InvalidInputException("All three arguments are required to add.");
        }
        if (isInvalidEmail(email)) {
            throw new InvalidInputException("Invalid email address.");
        }
        } catch (InvalidInputException e) {
            System.out.println(e.getMessage());
            return;
        }

        String fullEntry = String.format("%s %s %s", firstName, lastName,
                email);
        //add() is item method in receiver to append new item to list
        receiver.add(fullEntry);
        history.push(this);
        System.out.println("Add");
    }

    @Override
    public void undo() {
        receiver.list.remove(receiver.list.size() - 1);
    }


}
