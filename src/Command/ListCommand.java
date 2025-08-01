package Command;
import Receiver.Receiver;

public class ListCommand implements Command{
    private final Receiver receiver;

    public ListCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        receiver.list();
    }

    @Override
    public void undo() {
    }
}
