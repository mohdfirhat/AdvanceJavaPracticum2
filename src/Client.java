import Command.Command;
import Invoker.Invoker;
import Receiver.Receiver;
import Command.*;

import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Client {
    public static void main(String[] args) {
        Stack<Command> history = new Stack<>();

        Receiver reciever = new Receiver();
        reciever.readFile();
        System.out.println(reciever.getList());
        Invoker invoker = new Invoker();

        AddCommand add1 = new AddCommand(reciever,"Hello World Correct_Email@email.com");
        AddCommand add2 = new AddCommand(reciever,"Hello World2 _Wrong_Email@email.com");
        AddCommand add3 = new AddCommand(reciever,"Hello World3 Correct_Email@email.com");
        DeleteCommand delete1 = new DeleteCommand(reciever,"1");
        UpdateCommand update1 = new UpdateCommand(reciever,"2 Bye Hi");
        UndoCommand undo1 = new UndoCommand(history);
        ListCommand list1 = new ListCommand(reciever);
        invoker.setCmdToExecute(new Command[] {add1, add3, add3, delete1, list1, update1, list1, undo1, undo1, list1});
        invoker.executeCommand(history);

    }

}