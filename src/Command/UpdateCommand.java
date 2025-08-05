package Command;
import Receiver.Receiver;
import Exception.InvalidInputException;

import java.util.List;
import java.util.Stack;


public class UpdateCommand implements Command, Undoable {
    private Receiver receiver;
    private int index;
    private String originalEntry;
    private String[] inputArr;
    private List<String> items;
    private String updatedEntry;

    public UpdateCommand(Receiver receiver, String input) {
        items = receiver.list;
        this.receiver = receiver;
        this.inputArr = input.trim().replaceAll("\\s+", " ").split(" ");
    }

    @Override
    public void execute(Stack<Command> history) {
        try {
            this.index = Integer.parseInt(inputArr[0].trim()) - 1;
            if (this.index < 0 || this.index >= items.size()) {
                throw new InvalidInputException("invalid index");
            }

            if (inputArr.length > 4 || inputArr.length <= 1) {
                throw new InvalidInputException("invalid number of inputs given");
            }
        } catch (InvalidInputException e) {
            System.out.println(e.getMessage());
            return;
        } catch (NumberFormatException e) {
            //change error message
            System.out.println(e.getMessage());
            return;
        }
        this.originalEntry = receiver.list.get(this.index);
        String[] parts = originalEntry.split(" ", 3);
        String origFirstName = parts[0];
        String origLastName = parts[1];
        String origEmail = parts[2];



        if (inputArr.length == 4) {
            String newFirstName = titleCase(inputArr[1]);
            String newLastName = titleCase(inputArr[2]);
            String newEmail = titleCase(inputArr[3]);
            String updatedEntry = newFirstName + " " + newLastName + " " + newEmail;
        } else if (inputArr.length == 3){
            String newFirstName = titleCase(inputArr[1]);
            String newLastName = titleCase(inputArr[2]);
            String updatedEntry = newFirstName + " " + newLastName + " " + origEmail;
        }else if (inputArr.length == 2){
            String newFirstName = titleCase(inputArr[1]);
            String updatedEntry = newFirstName + " " + origLastName + " " + origEmail;
        }

        receiver.update(index, updatedEntry);
        history.push(this);
        System.out.println("Update");
    }

    @Override
    public void undo() {
        receiver.update(index, originalEntry);
    }
}
