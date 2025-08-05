import Command.Command;
import Invoker.Invoker;
import Receiver.Receiver;
import Command.AddCommand;
import Command.UndoCommand;
import Command.ListCommand;

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
        UndoCommand undo1 = new UndoCommand();
        ListCommand list1 = new ListCommand(reciever);
        invoker.setCmdToExecute(new Command[] {add1, add2, add3, add1, list1, undo1,undo1,list1});
        invoker.executeCommand(history);

    }

}