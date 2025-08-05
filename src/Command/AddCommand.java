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

        // Validate data
//        try {
            if (inputArr[0].isBlank() || inputArr[1].isBlank() || inputArr[2].isBlank()) {
//                throw new InvalidInputException("All three arguments are required to add.");
                System.out.println("All three arguments are required to add.");
            }
            if (isInvalidEmail(inputArr[2])) {
//                throw new InvalidInputException("Invalid email address.");
                System.out.println("Invalid email address.");
            }

            //set variables
            this.receiver = receiver;
            this.firstName = titleCase(inputArr[0]);
            this.lastName = titleCase(inputArr[1]);
            this.email = inputArr[2];
//        } catch (InvalidInputException e) {
//            System.out.println(e.getMessage());
//        }
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
