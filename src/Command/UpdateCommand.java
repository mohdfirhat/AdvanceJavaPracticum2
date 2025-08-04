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
        String[] parts = originalEntry.split(" ", 3);
        String origFirstName = parts.length > 0 ? parts[0] : "";
        String origLastName = parts.length > 1 ? parts[1] : "";
        String origEmail = parts.length > 2 ? parts[2] : "";

        this.firstName = !data1.isEmpty() ? data1 : origFirstName;
        this.lastName = !data2.isEmpty() ? data2 : origLastName;
        this.email = !data3.isEmpty() ? data3 : origEmail;

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
