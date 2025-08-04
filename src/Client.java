import Command.Command;
import Receiver.Receiver;

import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Client {
    public static void main(String[] args) {
        Stack<Command> history = new Stack<>();

        Receiver reciever = new Receiver();
        reciever.readFile();
        System.out.println(reciever.getList());
    }

}