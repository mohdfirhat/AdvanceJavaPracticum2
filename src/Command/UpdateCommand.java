<<<<<<< Updated upstream
package Command;
import Receiver.Receiver;

public class UpdateCommand implements Command {
    private Receiver receiver;
    private int index;
    private String firstName;
    private String lastName;
    private String email;
    private String originalEntry;

    public UpdateCommand(Receiver receiver, int index, String data1, String data2, String data3) {
        this.receiver = receiver;
        this.index = index;
        this.firstName = data1;
        this.lastName = data2;
        this.email = data3;
        //add condition for invalid index

    }

    @Override
    public void execute() {
        originalEntry = receiver.list.get(index);
        //KIV may be better to define list as ArrayList(String[]) instead of ArrayList(String)
    }

    @Override
    public void undo() {}
}
=======
package Command;

public class UpdateCommand implements Command, Undoable {
    @Override
    public void execute() {

    }

    @Override
    public void undo() {

    }

}
>>>>>>> Stashed changes
