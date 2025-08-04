package Command;
import Receiver.Receiver;
import Exception.InvalidInputException;

import java.util.List;
import java.util.Stack;


public class UpdateCommand implements Command, Undoable {
    private Receiver receiver;
    private int index;
    private String firstName;
    private String lastName;
    private String email;
    private String originalEntry;
    private List<String> items;

    public UpdateCommand(Receiver receiver, int index, String data1) {
        items = receiver.list;
        if (index < 0 || index >= items.size()) {
            throw new  InvalidInputException("invalid index");
        }

        this.receiver = receiver;
        this.index = index-1;

        this.originalEntry = receiver.list.get(index);
        String[] parts = originalEntry.split(" ", 3);
        String origLastName = parts[1];
        String origEmail = parts[2];

        this.firstName = data1;
        this.lastName = origLastName;
        this.email = origEmail;

    }

    public UpdateCommand(Receiver receiver, int index, String data1, String data2) {
        items = receiver.list;
        if (index < 0 || index >= items.size()) {
            throw new  InvalidInputException("invalid index");
        }

        this.receiver = receiver;
        this.index = index-1;

        this.originalEntry = receiver.list.get(index);
        String[] parts = originalEntry.split(" ", 3);
        String origEmail = parts[2];

        this.firstName = data1;
        this.lastName = data2;
        this.email = origEmail;

    }

    public UpdateCommand(Receiver receiver, int index, String data1, String data2, String data3) {
        items = receiver.list;
        if (index < 0 || index >= items.size()) {
            throw new  InvalidInputException("invalid index");
        }
        //add condition to check data3 email regex
        if (isInvalidEmail(data3)) {
            throw new  InvalidInputException("invalid email");
        }
        this.receiver = receiver;
        this.index = index-1;

        this.originalEntry = receiver.list.get(index);

        this.firstName = data1;
        this.lastName = data2;
        this.email = data3;

    }

    @Override
    public void execute(Stack<Command> history) {
        String updatedEntry = firstName + " " + lastName + " " + email;
        receiver.update(index, updatedEntry);
        history.push(this);
        System.out.println("Update");
    }

    @Override
    public void undo() {
        receiver.update(index, originalEntry);
    }
}
