import Command.Command;
import Invoker.Invoker;
import Receiver.Receiver;
import Command.*;

import java.util.*;

/**
 * The driver class to invoke the commands.
 *
 */
public class Client {
    /**
     * Constructs a new Client object.
     */
    public Client() {
        // Constructor body (if any initialization is needed)
    }
    /**
     * The main method creates a receiver and invoker object to pass into commands
     * @param args argument for driver class
     */
    public static void main(String[] args) {
        /**
         * variable to store undoable commands.
         */
        Stack<Command> history = new Stack<>();
        /**
         * variable for the receiver
         */
        Receiver receiver = new Receiver();/**
         /**
         * variable for the invoker
         */
        Invoker invoker = new Invoker();


        AddCommand add1 = new AddCommand(receiver,"First_name Last_name Email");
        AddCommand add2 = new AddCommand(receiver,"John Doe simple@example.com");
        AddCommand add3 = new AddCommand(receiver,"Hanna Moon tetter.tots@potatoesarelife.com");
        AddCommand add4 = new AddCommand(receiver,"Ah Boon green-tea@teaforlife.com");
        UpdateCommand update1 = new UpdateCommand(receiver,"3 Adam");
        UpdateCommand update2 = new UpdateCommand(receiver,"1 blue bell ice-cream@alaskaFields.org");
        UndoCommand undo1 = new UndoCommand(receiver, history);
        ListCommand list1 = new ListCommand(receiver);
        DeleteCommand delete1 = new DeleteCommand(receiver, "1 ");

        AddCommand adda = new AddCommand(receiver, "51323 dOe.2f notemail");
        UpdateCommand updatea = new UpdateCommand(receiver, "1 43143 n4ame invalid..email@x.com");
        UpdateCommand updateb = new UpdateCommand(receiver, "1 5321 n4ame valid.email@x.com");
        //invoker.setCommandsForExecution(new Command[]{add1, add2, add3, add4, list1, update1, list1, update2, list1, delete1, list1, undo1, list1});
        invoker.setCommandsForExecution(new Command[] {delete1, list1});


        invoker.executeCommand(history);

        receiver.storeToFile();

    }

}