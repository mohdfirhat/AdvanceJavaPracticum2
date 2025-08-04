package Command;
import Receiver.Receiver;
import Exception.InvalidInputException;

import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddCommand implements Command , Undoable {
    private final Receiver receiver;
    private final String firstName, lastName, email;

    public AddCommand(Receiver receiver, String data1, String data2, String data3){
        // Sanitize data
        data1 = data1.trim();
        data2 = data2.trim();
        data3 = data3.trim();

        // Validate data
        try {
            if (data1.isBlank() || data2.isBlank() || data3.isBlank()) {
                throw new InvalidInputException("All three arguments are required to add.");
            }
            if (isInvalidEmail(data3)) {
                throw new InvalidInputException("Invalid email address.");
            }
        } catch (InvalidInputException e) {
            System.out.println(e.getMessage());
        }

        //set variables
        this.receiver = receiver;
        this.firstName = data1;
        this.lastName = data2;
        this.email = data3;
    }

    @Override
    public void execute(Stack<Command> history) {
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
