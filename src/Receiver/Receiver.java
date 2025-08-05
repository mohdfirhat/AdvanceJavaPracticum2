package Receiver;

import Command.Command;
import java.io.*;
import java.util.ArrayList;
import java.util.Stack;

public class Receiver {

    private static final String FILE_PATH = "./src/dataStore.txt";
    public final ArrayList<String> list = new ArrayList<>();
    public Stack<Command> history = new Stack<>();

    public Receiver() {
    }

    public void readFile() {

        try (
                FileReader fr = new FileReader(FILE_PATH);
                BufferedReader br = new BufferedReader(fr)) {

            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim().replaceAll("^[0-9]+[.]\\s", "");
                list.add(line);
            }

        } catch (IOException fnf) {
            //TODO: May need to handle IOException and FileNotFoundException individually
            System.err.println("Unable to read file.File not found.");
            fnf.printStackTrace();
        }

    }

    public void storeToFile() {
        try (FileWriter fw = new FileWriter(FILE_PATH);
             BufferedWriter bw = new BufferedWriter(fw)) {
            for (int i=0; i<list.size(); i++) {
                bw.write(String.format("%02d. %s\n",i+1,list.get(i)));
            }
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    public ArrayList<String> getList() {
        return list;
    }

    public boolean add(String entry){
        list.add(entry);
        return true;
    }

    public boolean delete(int index){
        list.remove(index);
        return true;

    }

    public boolean list(){
        for (int i = 0; i < list.size(); i++) {
            int count = i+1;
            System.out.println(String.format("%02d. %s", count, list.get(i)));
        }
        return true;
    }

    public boolean update(int index, String entry){
        list.set(index, entry);
        return true;
    }
}