package by.ivanov.internetshop.dao.mysql;


public class MySQLException extends Exception {

    public MySQLException(String string) {
        super(string);
    }

    public MySQLException(Throwable thrwbl) {
        super(thrwbl);
    }

    public MySQLException(String string, Throwable thrwbl) {
        super(string, thrwbl);
    }
}
