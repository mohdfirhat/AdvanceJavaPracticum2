package Command;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import Exception.InvalidInputException;

/**
 * An abstract class used to create concrete command classes.
 * The abstract class also provides 2 helper methods (to be used
 * in command classes.
 */

public interface Command {

    /**
     * Abstract method for the concrete classes to implement their own
     * setup procedures of the execute command.
     */
    void execute() throws InvalidInputException;
    /**
     * Abstract method for the concrete classes to implement their own
     * setup procedures of the undo command if applicable.
     */
    void undo();

    /**
     * Abstract method to flag if a command is undoable.
     * @return true if command is unundoable
     */
    boolean isUndoable();

    /**
     * Helper method to check if a data3 input is valid.
     * the left side checks the email, while the right
     * side checks whether the inputs are legal (contains only alphanumerics or _).
     * @param email email string the check the regex
     * @return true if pattern does not match
     */
    default boolean isInvalidData3(String email) {
        Pattern pattern = Pattern.compile("(^(?![.-])" +
                "[a-zA-Z0-9_]+(?:[.-](?![.-])[a-zA-Z0-9_]+)*" +
                "(?<![.-])@" +
                "(?![.-])" +
                "[a-zA-Z0-9]+(?:[." +
                "-](?![.-])[a-zA-Z0-9]+)*" +
                "(?<![.-])" +
                "\\.([a-z]{2,3})$|" +
                "^[A-Za-z0-9_]+$)");
        Matcher matcher = pattern.matcher(email);
        return !matcher.find();
    }
    /**
     * Helper method to parse a string into titleCase.
     * @param data user input to check titlecase
     * @return the titlecased word
     */
    default String titleCase(String data) {
        data = data.toLowerCase();
        String firstWord = data.substring(0, 1).toUpperCase();
        String restOfWord = data.substring(1);
        return firstWord + restOfWord;

    }

}
