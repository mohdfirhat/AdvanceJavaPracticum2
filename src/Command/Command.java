package Command;

import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface Command {
    void execute(Stack<Command> history);
    //Helper Method
    default boolean isInvalidEmail(String email) {
        Pattern pattern = Pattern.compile("^(?![._-])" +
                "[a-zA-Z0-9]+(?:[._-](?![._-])[a-zA-Z0-9]+)*" +
                "(?<![._-])@" +
                "(?![._-])" +
                "[a-zA-Z0-9]+(?:[._-](?![._-])[a-zA-Z0-9]+)*" +
                "(?<![._-])" +
                "\\.([a-z]{2,3})$");
        Matcher matcher = pattern.matcher(email);

        return !matcher.find();
    }
    void undo();

}
