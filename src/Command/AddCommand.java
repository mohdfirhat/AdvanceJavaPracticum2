<<<<<<< Updated upstream
package Command;
import Receiver.Receiver;

public class AddCommand implements Command {
    private final Receiver receiver;
    private final String firstName, lastName, email;
    private int index;

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
        //getItems() is geter items for total # of items in receiver
        index = receiver.getList().size() - 1;
        System.out.println("add");
    }

    @Override
    public void undo() {
        receiver.list.remove(receiver.list.size() - 1);
    }
}
=======
package Command;

public class AddCommand implements Command , Undoable {

    @Override
    public void execute() {

    }

    @Override
    public void undo() {

    }
}
>>>>>>> Stashed changes
