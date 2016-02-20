package by.ivanov.internetshop.dao.util;

public class SQLRequest {
    public static final String ADD_USER = "INSERT INTO user   VALUES ( ?, ?, ?, ?, ?, ?, ?) ";
    public static final String GET_USER_WITH_LOGIN_PASSWORD = "SELECT * FROM user WHERE login = ? AND password = ? ";
    public static final String GET_PRODUCT ="SELECT * FROM product";
    public static final String ADD_TO_BASKET = "INSERT INTO basket   VALUES ( ?, ?, ?) ";

    public static final String BLOCKUSER = "INSERT INTO locked_users   VALUES ( ?, ?, ?) ";

    public static final String GET_USERS ="select * from user u left outer join  locked_users l ON l.id_lockeduser=u.id_user  where l.id_lockeduser is null and u.role='user'";





}
