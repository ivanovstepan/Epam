package by.ivanov.internetshop.dao.mysql;


public class MySQLDBException extends Exception {

    public MySQLDBException(String string) {
        super(string);
    }

    public MySQLDBException(Throwable thrwbl) {
        super(thrwbl);
    }

    public MySQLDBException(String string, Throwable thrwbl) {
        super(string, thrwbl);
    }
}
