package by.ivanov.internetshop.dao;


public class DatabaseException extends Exception {
    public DatabaseException(String string) {
        super(string);
    }

    public DatabaseException(Throwable thrwbl) {
        super(thrwbl);
    }

    public DatabaseException(String string, Throwable thrwbl) {
        super(string, thrwbl);
    }
}
