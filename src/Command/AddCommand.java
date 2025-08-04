package Command;
import Receiver.Receiver;

public class AddCommand implements Command , Undoable {
    private final Receiver receiver;
    private final String firstName, lastName, email;

    public AddCommand(Receiver receiver, String data1, String data2, String data3) {
        //add regex condition here for email
        this.receiver = receiver;
        this.firstName = data1;
        this.lastName = data2;
        this.email = data3;
    }

    @Override
    public void execute() {
        String fullEntry = firstName + "," + lastName + "," + email;
        //add() is item method in receiver to append new item to list
        receiver.add(fullEntry);
        System.out.println("add");
    }

    @Override
    public void undo() {
        receiver.list.remove(receiver.list.size() - 1);
    }
}
