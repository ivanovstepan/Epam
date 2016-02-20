package by.ivanov.internetshop.dao;

public class DaoFactory {
    private volatile static boolean instanceCreated = false;
    private static DaoFactory instance;
    public static DaoFactory getInstance()  {
        if (!instanceCreated) {
                instance = new DaoFactory();
                instanceCreated = true;
            }
        return instance;
    }
    public static MySQLDBDao getSQLDBDao()
    {
        MySQLDBDao mySQLDBDao = MySQLDBDao.getInstance();
                return  mySQLDBDao;
    }
}
