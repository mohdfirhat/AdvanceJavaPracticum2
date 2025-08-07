package Command;
import Receiver.Receiver;
import Exception.InvalidInputException;
import java.util.List;


public class UpdateCommand implements Command {
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
    public void execute() throws InvalidInputException {
        try {
            this.index = Integer.parseInt(inputArr[0].trim()) - 1;
            if (this.index < 0 || this.index >= items.size()) {
                throw new InvalidInputException("invalid index");
            }

            if (inputArr.length > 4 || inputArr.length <= 1) {
                throw new InvalidInputException("invalid number of inputs given");
            }

            if (inputArr.length == 4) {
                if (isInvalidData3(inputArr[3])) {
                    throw new InvalidInputException("Invalid email address.");
                }
            }
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Invalid number format");
        }


        this.originalEntry = receiver.list.get(this.index);
        String[] parts = originalEntry.split(" ", 3);
        String origFirstName = parts[0];
        String origLastName = parts[1];
        String origEmail = parts[2];
        String newEmail;

        if (inputArr.length == 4) {
            String newFirstName = titleCase(inputArr[1]);
            String newLastName = titleCase(inputArr[2]);

            if (inputArr[3].contains("@")){
                newEmail = inputArr[3];
            }else{
                newEmail = titleCase(inputArr[3]);}
            this.updatedEntry = newFirstName + " " + newLastName + " " + newEmail;
        } else if (inputArr.length == 3){
            String newFirstName = titleCase(inputArr[1]);
            String newLastName = titleCase(inputArr[2]);
            this.updatedEntry = newFirstName + " " + newLastName + " " + origEmail;
        }else if (inputArr.length == 2){
            String newFirstName = titleCase(inputArr[1]);
            this.updatedEntry = newFirstName + " " + origLastName + " " + origEmail;
        }

        receiver.update(index, updatedEntry);
        System.out.println("update");
    }

    @Override
    public void undo() {
        receiver.update(index, originalEntry);
    }
    public boolean isUndoable() {
        return true;
    }
}
