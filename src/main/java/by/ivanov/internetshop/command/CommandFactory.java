package by.ivanov.internetshop.command;


public class CommandFactory {
    private volatile static boolean instanceCreated = false;
    private static CommandFactory instance;

    public static CommandFactory getInstance() {
        if (!instanceCreated) {
                instance = new CommandFactory();
                instanceCreated = true;
        }
        return instance;
    }

    public Command createCommand(String commandName) {
        Command command = null;
        CommandType type = CommandType.valueOf(commandName.toUpperCase());

        if (type != null) {
            command = type.getCommand();
        }
        return command;
    }
}
