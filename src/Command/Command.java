package Command;

import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import Exception.InvalidInputException;

public interface Command {
    void execute() throws InvalidInputException;

    void undo();

    boolean isUndoable();

    //Helper Method
    default boolean isInvalidEmail(String email) {
        Pattern pattern = Pattern.compile("^(?![._-])" +
                "[a-zA-Z0-9]+(?:[._-](?![._-])[a-zA-Z0-9]+)*" +
                "(?<![._-])@" +
                "(?![._-])" +
                "[a-zA-Z0-9]+(?:[._" +
                "-](?![._-])[a-zA-Z0-9]+)*" +
                "(?<![._-])" +
                "\\.([a-z]{2,3})$");
        Matcher matcher = pattern.matcher(email);
        return !matcher.find();
    }

    default String titleCase(String data) {
        data = data.toLowerCase();
        String firstWord = data.substring(0, 1).toUpperCase();
        String restOfWord = data.substring(1);
        return firstWord + restOfWord;

    }

}
