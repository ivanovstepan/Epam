package by.ivanov.internetshop.dao;

import by.ivanov.internetshop.dao.mysql.MySQLDao;

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
    public static MySQLDao getSQLDBDao()
    {
        MySQLDao SQLDao = MySQLDao.getInstance();
                return SQLDao;
    }
}
