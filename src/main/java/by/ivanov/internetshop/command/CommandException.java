package by.ivanov.internetshop.command;


public class CommandException extends Exception {
    public CommandException(String string) {
        super(string);
    }

    public CommandException(String string, Throwable thrwbl) {
        super(string, thrwbl);
    }

    public CommandException(Throwable thrwbl) {
        super(thrwbl);
    }
}
