package Command;

import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface Command {
    void execute(Stack<Command> history);
    //Helper Method
    default boolean isInvalidEmail(String email) {
        Pattern pattern = Pattern.compile("^((?!.*[.-_]{2})[A-Za-z0-9][A-Za-z0-9._-]+[A-Za-z0-9])@((?!.*[.-]{2})[A-Za-z0-9][A-Za-z0-9.-]+[A-Za-z0-9])\\.[a-z]{2,3}$");
        Matcher matcher = pattern.matcher(email);

        return !matcher.find();
    }
    void undo();

}
