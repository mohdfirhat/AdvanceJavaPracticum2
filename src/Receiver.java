import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Receiver {

    private static final String FILE_PATH = "./src/dataStore.txt";
    private final ArrayList<String> list = new ArrayList<>();

    public Receiver() {
    }

    public void readFile() {

        try (
                FileReader fr = new FileReader(FILE_PATH);
                BufferedReader br = new BufferedReader(fr)) {

            String line;
            while ((line = br.readLine()) != null) {
                list.add(line);
            }

        } catch (IOException fnf) {
            //TODO: May need to handle IOException and FileNotFoundException individually
            System.err.println("Unable to read file.File not found.");
            return;
        }

    }

    public ArrayList<String> getList() {
        return list;
    }
}
