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

        AddCommand add1 = new AddCommand(reciever,"First_name Last_name Email");
        AddCommand add2 = new AddCommand(reciever,"John Doe simple@example.com");
        AddCommand add3 = new AddCommand(reciever,"Hanna Moon tetter.tots@potatoesarelife.com");
        AddCommand add4 = new AddCommand(reciever,"Ah Boon green-tea@teaforlife.com");
        UpdateCommand update1 = new UpdateCommand(reciever,"3 Adam");
        UpdateCommand update2 = new UpdateCommand(reciever,"1 blue bell ice-cream@alaskaFields.org");
        UndoCommand undo1 = new UndoCommand(reciever, history);
        ListCommand list1 = new ListCommand(reciever);
        DeleteCommand delete1 = new DeleteCommand(reciever, "1");
        invoker.setCmdToExecute(new Command[]{add1, add2, add3, add4, list1, update1, list1, update2, list1, delete1, list1, undo1, list1});
        //invoker.setCmdToExecute(new Command[] {add1, add3, add3, delete1, list1, update1, list1, undo1, undo1, list1});
        invoker.executeCommand(history);

        reciever.storeToFile();

    }

}