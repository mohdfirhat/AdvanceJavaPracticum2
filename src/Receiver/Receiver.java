package Receiver;

import Command.Command;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import Exception.InvalidInputException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Receiver {

    private static final String FILE_PATH = "./src/dataStore.txt";
    public final ArrayList<String> list = new ArrayList<>();

    public Receiver() {
    }

    public void readFile() {
        Path filepath = Paths.get(FILE_PATH);
        String line = "";

        if  (Files.notExists(filepath)) {
            try {
                Files.createFile(filepath);
            } catch (IOException io) {
                System.out.println("Unable to create dataStore.txt file");
                return;
            } catch (SecurityException se) {
                System.out.println("You do not have access to create dataStore.txt file");
            } catch (UnsupportedOperationException uoe) {
                System.out.println("Error creating dataStore.txt file");
            }
        }

        try ( BufferedReader br = Files.newBufferedReader(filepath) ) {
            while ((line = br.readLine()) != null) {
                line = line.trim().replaceAll("^[0-9]+[.]\\s", "").replaceAll("\\s", " ");
                try {
                    list.add(checkAndFormatInput(line));
                } catch (InvalidInputException ie) {
                    System.out.println(ie.getMessage());
                }
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

    private String checkAndFormatInput(String line) throws InvalidInputException {
        String[] inputArr = line.split(" ");
        if (inputArr.length != 3) {
            throw new InvalidInputException("Invalid number of input in line " +
                    "at dataStore.txt");
        }
        if (isInvalidData3(inputArr[2])) {
            throw new InvalidInputException("Data 3 is not a valid entry");
        }
        String email;
        if (inputArr[2].contains("@")){
            email = inputArr[2];
        }else{
            email = titleCase(inputArr[2]);
        }
        return String.format("%s %s %s", titleCase(inputArr[0]), titleCase(inputArr[1]),email);
    }

    private boolean isInvalidData3(String email) {
        Pattern pattern = Pattern.compile("(^(?![.-])" +
                "[a-zA-Z0-9_]+(?:[.-](?![.-])[a-zA-Z0-9_]+)*" +
                "(?<![.-])@" +
                "(?![.-])" +
                "[a-zA-Z0-9]+(?:[." +
                "-](?![.-])[a-zA-Z0-9]+)*" +
                "(?<![.-])" +
                "\\.([a-z]{2,3})$|" +
                "^[A-Za-z0-9_]+)");
        Matcher matcher = pattern.matcher(email);
        return !matcher.find();
    }

    private String titleCase(String data) {
        data = data.toLowerCase();
        String firstWord = data.substring(0, 1).toUpperCase();
        String restOfWord = data.substring(1);
        return firstWord + restOfWord;

    }

}