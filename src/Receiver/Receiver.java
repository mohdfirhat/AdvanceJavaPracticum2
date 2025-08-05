package Receiver;

import Command.Command;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Stack;

public class Receiver {

    private static final String FILE_PATH = "./src/dataStore.txt";
    public final ArrayList<String> list = new ArrayList<>();
    public Stack<Command> history = new Stack<>();

    public Receiver() {
    }

    public void readFile() {
        Path filepath = Paths.get(FILE_PATH);
        String line = "";

        if  (Files.notExists(filepath)) {
            System.out.println("dataStore.txt does not exist");
            return;
        }

        try ( BufferedReader br = Files.newBufferedReader(filepath) ) {
            while ((line = br.readLine()) != null) {
                line = line.trim().replaceAll("^[0-9]+[.]\\s", "");
                list.add(line);
            }

        } catch (IOException io) {
            System.out.println("An error has occurred while reading file");
        } catch (SecurityException se) {
            System.out.println("You do not have read access to the file.");
        }

    }

    public void storeToFile() {
        Path filepath = Paths.get(FILE_PATH);
        String line = "";

        try (BufferedWriter buff_writer = Files.newBufferedWriter(filepath)) {
            for (int i=0; i<list.size(); i++) {
                buff_writer.write(String.format("%02d. %s",i+1,list.get(i)));
                buff_writer.newLine();
            }
        } catch (IOException io) {
            System.out.println("An error has occurred while writing file");
        } catch (SecurityException se) {
            System.out.println("You do not have write access to the file");
        } catch (IllegalArgumentException iae) {
            System.out.println("You are unable to create, truncate or write " +
                    "in this file");
        } catch (UnsupportedOperationException uoe) {
            System.out.println("Unsupported operation on the data store");
        }
    }

    public ArrayList<String> getList() {
        return list;
    }

    public void add(String entry){
        list.add(entry);
    }

    public void add(int index, String entry){
        list.add(index,entry);
    }


    public void delete(int index){
        list.remove(index);

    }

    public void list(){
        for (int i = 0; i < list.size(); i++) {
            int count = i+1;
            System.out.println(String.format("%02d. %s", count, list.get(i)));
        }
    }

    public void update(int index, String entry){
        list.set(index, entry);
    }
}