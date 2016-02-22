package by.ivanov.internetshop.command;

import by.ivanov.internetshop.command.command.*;

public enum CommandType {
        LOGIN(new Login()),
        REGISTRATION(new Register()),
        ADDTOBASKET(new AddToBasket()),
        BLOCKUSER(new BlockUser()),
        LOCALE (new Locale()),
        LOGOUT(new Logout()),
        GOTOBASKET(new GoToBasket()),
        ADDPRODUCT(new AddProduct());
        private Command command;
        private CommandType(Command command) {
            this.command = command;
        }
        public Command getCommand() {
            return command;
        }

}
