package by.ivanov.internetshop.command;

import by.ivanov.internetshop.command.command.*;

public enum CommandType {
        LOGIN(new Login()),
        REGISTRATION(new Register()),
        ADDTOBASKET(new AddToBasket()),
        BLOCKUSER(new BlockUser()),
        LOCALE (new Locale());
        private Command command;
        private CommandType(Command command) {
            this.command = command;
        }
        public Command getCommand() {
            return command;
        }

}
