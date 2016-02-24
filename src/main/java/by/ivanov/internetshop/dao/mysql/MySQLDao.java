package by.ivanov.internetshop.dao.mysql;
import by.ivanov.internetshop.dao.DBDao;
import by.ivanov.internetshop.dao.DatabaseException;
import by.ivanov.internetshop.dao.util.DBColumnName;
import by.ivanov.internetshop.dao.util.SQLRequest;
import by.ivanov.internetshop.entity.Product;
import by.ivanov.internetshop.entity.User;
import by.ivanov.internetshop.generator.Generator;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class MySQLDao implements DBDao {
    private static final Logger logger = Logger.getRootLogger();
    private static MySQLDao instance;
    private volatile static boolean instanceCreated = false;
    private static MySQLPoolConnection poolConnections;


    public static MySQLDao getInstance() {
        if (!instanceCreated) {
            instance = new MySQLDao();
            try {
                poolConnections = new MySQLPoolConnection();
            } catch (Exception e) {
                e.printStackTrace();
            }
            instanceCreated = true;
        }
        return instance;
    }

    public static void init() throws Exception {
        poolConnections = new MySQLPoolConnection();
    }

    public static void destroy() throws SQLException {
        poolConnections.destroy();
    }

    public void addUser(User user) throws DatabaseException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = poolConnections.getConnection();
            preparedStatement = connection.prepareStatement(SQLRequest.ADD_USER);
            preparedStatement.setInt(1, user.getId());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3, user.getSurname());
            preparedStatement.setString(4, user.getLogin());
            preparedStatement.setString(5, user.getPassword());
            preparedStatement.setString(6, user.getEmail());
            preparedStatement.setString(7, user.getRole());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("SQLException is thrown when trying "+ SQLRequest.ADD_USER,e);
            throw new DatabaseException("SQLException is thrown when trying" + SQLRequest.ADD_USER, e);
        } catch (MySQLException e) {
            logger.error("MySQLDBDaoException is thrown when trying to take connection", e);
            throw new DatabaseException("MySQLDBDaoException is thrown when trying to take connection", e);
        }finally {
            try {
                if (preparedStatement != null) {
                    if (!preparedStatement.isClosed()) {
                        preparedStatement.close();
                    }
                }
                if (connection != null) {
                    poolConnections.putConnection(connection);
                }
            } catch (MySQLException e) {
                logger.error("MySQLDaoException is thrown when trying to put connection ", e);
                throw new DatabaseException("MySQLDBDaoException is thrown when trying to put connection ", e);
            } catch (SQLException e) {
                logger.error("SQLException is thrown when closing a statement", e);
                throw new DatabaseException("SQLException is thrown when closing a statement or ResultSet", e);
            }
        }
    }

    @Override
    public User findUser(String login, String password) throws DatabaseException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet userResult = null;
        User user = null;
        try {
            connection = poolConnections.getConnection();
            preparedStatement = connection.prepareStatement(SQLRequest.GET_USER_WITH_LOGIN_PASSWORD);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            userResult = preparedStatement.executeQuery();
            if (userResult.next()) {
                user = initUser(userResult);
            }
            return user;
        } catch (SQLException e) {
            logger.error("SQLException is thrown when trying " +SQLRequest.GET_USER_WITH_LOGIN_PASSWORD,e);
            throw new DatabaseException("SQLException is thrown when trying" + SQLRequest.GET_USER_WITH_LOGIN_PASSWORD, e);
        } catch (MySQLException e) {
            logger.error("MySQLDBDaoException is thrown when trying to take connection", e);
            throw new DatabaseException("MySQLDBDaoException is thrown when trying to take connection", e);
        }  finally {
            try {
                if (preparedStatement != null) {
                    if (!preparedStatement.isClosed()) {
                        preparedStatement.close();
                    }
                }
                if (userResult != null) {
                    if (!userResult.isClosed()) {
                        userResult.close();
                    }
                }
                if (connection != null) {
                    poolConnections.putConnection(connection);
                }
            } catch (MySQLException e) {
                logger.error("MySQLDaoException is thrown when trying to put connection ", e);
                throw new DatabaseException("MySQLDBDaoException is thrown when trying to put connection ", e);
            } catch (SQLException e) {
                logger.error("SQLException is thrown when closing a statement", e);
                throw new DatabaseException("SQLException is thrown when closing a statement or ResultSet", e);
            }
        }
    }

    private User initUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt(DBColumnName.ID_USER));
        user.setLogin(resultSet.getString(DBColumnName.LOGIN_USER));
        user.setPassword(resultSet.getString(DBColumnName.PASSWORD_USER));
        user.setName(resultSet.getString(DBColumnName.FIRST_NAME_USER));
        user.setSurname(resultSet.getString(DBColumnName.LAST_NAME_USER));
        user.setRole(resultSet.getString(DBColumnName.ROLE));
        user.setEmail(resultSet.getString(DBColumnName.EMAIL));
        return user;
    }

    private Product initProduct(ResultSet resultSet) throws SQLException {
        Product product = new Product();
        product.setId_product(resultSet.getLong(DBColumnName.ID_PRODUCT));
        product.setAmount(resultSet.getLong(DBColumnName.AMOUNT));
        product.setDescription(resultSet.getString(DBColumnName.DESCRIPTION));
        product.setPrice(resultSet.getInt(DBColumnName.PRICE));
        return product;
    }

    @Override
    public List<Product> getProduct() throws DatabaseException {
        List<Product> list = new ArrayList<Product>();
        Product product;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultProduct;
        try {
            connection = poolConnections.getConnection();
            preparedStatement = connection.prepareStatement(SQLRequest.GET_PRODUCT);
            resultProduct = preparedStatement.executeQuery();
            while (resultProduct.next()) {
                product = initProduct(resultProduct);
                list.add(product);
            }
        } catch (SQLException e) {
            logger.error("SQLException is thrown when trying "+SQLRequest.GET_PRODUCT,e);
            throw new DatabaseException("SQLException is thrown when trying" + SQLRequest.GET_PRODUCT, e);
        } catch (MySQLException e) {
            logger.error("MySQLDBDaoException is thrown when trying to take connection", e);
            throw new DatabaseException("MySQLDBDaoException is thrown when trying to take connection", e);
        } finally {
            try {
                if (preparedStatement != null) {
                    if (!preparedStatement.isClosed()) {
                        preparedStatement.close();
                    }
                }
                if (connection != null) {
                    poolConnections.putConnection(connection);
                }
            } catch (MySQLException e) {
                logger.error("MySQLDaoException is thrown when trying to put connection ", e);
                throw new DatabaseException("MySQLDBDaoException is thrown when trying to put connection ", e);
            } catch (SQLException e) {
                logger.error("SQLException is thrown when closing a statement", e);
                throw new DatabaseException("SQLException is thrown when closing a statement or ResultSet", e);
            }
        }
        return list;
    }
    @Override
    public void addOrder(User user,Map<String,String[]> map) throws DatabaseException {
        String[] idPoduct = map.get("product");
        String[] count = map.get("count");

        for (int i = 0; i < idPoduct.length ; i++) {
            int amount = Integer.parseInt(count[i]);
            if (amount != 0) {
                Connection connection = null;
                PreparedStatement preparedStatement = null;
                try {
                    connection = poolConnections.getConnection();
                    preparedStatement = connection.prepareStatement(SQLRequest.ADD_TO_BASKET);
                    preparedStatement.setLong(1, Long.parseLong(idPoduct[i]));
                    preparedStatement.setLong(2, user.getId());
                    preparedStatement.setInt(3, amount);
                    preparedStatement.executeUpdate();

                } catch (SQLException e) {
                    logger.error("SQLException is thrown when trying " + SQLRequest.ADD_TO_BASKET,e);
                    throw new DatabaseException("SQLException is thrown when trying" + SQLRequest.ADD_TO_BASKET, e);
                } catch (MySQLException e) {
                    logger.error("MySQLDBDaoException is thrown when trying to take connection", e);
                    throw new DatabaseException("MySQLDBDaoException is thrown when trying to take connection", e);
                } finally {
                    try {
                        if (preparedStatement != null) {
                            if (!preparedStatement.isClosed()) {
                                preparedStatement.close();
                            }
                        }
                        if (connection != null) {
                            poolConnections.putConnection(connection);
                        }
                    } catch (MySQLException e) {
                        logger.error("MySQLDaoException is thrown when trying to put connection ", e);
                        throw new DatabaseException("MySQLDBDaoException is thrown when trying to put connection ", e);
                    } catch (SQLException e) {
                        logger.error("SQLException is thrown when closing a statement", e);
                        throw new DatabaseException("SQLException is thrown when closing a statement or ResultSet", e);
                    }
                }
            }
        }
    }

    @Override
    public List<User> getUsers() throws DatabaseException {
        List<User> list = new ArrayList<User>();
        User user ;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultProduct;
        try {
            connection = poolConnections.getConnection();
            preparedStatement = connection.prepareStatement(SQLRequest.GET_USERS);
            resultProduct = preparedStatement.executeQuery();
            while (resultProduct.next()) {
                user = initUser(resultProduct);
                list.add(user);
            }
        } catch (SQLException e) {
            logger.error("SQLException is thrown when trying " + SQLRequest.GET_USERS,e);
            throw new DatabaseException("SQLException is thrown when trying" + SQLRequest.GET_USERS, e);
        } catch (MySQLException e) {
            logger.error("MySQLDBDaoException is thrown when trying to take connection", e);
            throw new DatabaseException("MySQLDBDaoException is thrown when trying to take connection", e);
        } finally {
            try {
                if (preparedStatement != null) {
                    if (!preparedStatement.isClosed()) {
                        preparedStatement.close();
                    }
                }
                if (connection != null) {
                    poolConnections.putConnection(connection);
                }
            } catch (MySQLException e) {
                logger.error("MySQLDaoException is thrown when trying to put connection ", e);
                throw new DatabaseException("MySQLDBDaoException is thrown when trying to put connection ", e);
            } catch (SQLException e) {
                logger.error("SQLException is thrown when closing a statement", e);
                throw new DatabaseException("SQLException is thrown when closing a statement or ResultSet", e);
            }
        }
        return list;
    }


    @Override
    public void blockUser(Map<String,String[]> map, int idAdmin) throws DatabaseException {

        String[] idUser = map.get("block");
        String[] descriptions = map.get("description");
        String description ;
        for (int i = 0; i < idUser.length; i++) {
            description = descriptions[i];
            if (!description.equals("")) {
                Connection connection = null;
                PreparedStatement preparedStatement = null;
                try {
                    connection = poolConnections.getConnection();
                    preparedStatement = connection.prepareStatement(SQLRequest.BLOCKUSER);
                    preparedStatement.setLong(1, Long.parseLong(idUser[i]));
                    preparedStatement.setLong(2, idAdmin);
                    preparedStatement.setString(3, description);
                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    logger.error("SQLException is thrown when trying " + SQLRequest.BLOCKUSER,e);
                    throw new DatabaseException("SQLException is thrown when trying" + SQLRequest.BLOCKUSER, e);
                } catch (MySQLException e) {
                    logger.error("MySQLDBDaoException is thrown when trying to take connection", e);
                    throw new DatabaseException("MySQLDBDaoException is thrown when trying to take connection", e);
                } finally {
                    try {
                        if (preparedStatement != null) {
                            if (!preparedStatement.isClosed()) {
                                preparedStatement.close();
                            }
                        }
                        if (connection != null) {
                            poolConnections.putConnection(connection);
                        }
                    } catch (MySQLException e) {
                        logger.error("MySQLDaoException is thrown when trying to put connection ", e);
                        throw new DatabaseException("MySQLDBDaoException is thrown when trying to put connection ", e);
                    } catch (SQLException e) {
                        logger.error("SQLException is thrown when closing a statement", e);
                        throw new DatabaseException("SQLException is thrown when closing a statement or ResultSet", e);
                    }
                }
            }
        }
    }



    @Override
    public List<Product> getOrder(int id) throws DatabaseException {
        List<Product> list = new ArrayList<Product>();
        Product product = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultProduct = null;
        try {
            connection = poolConnections.getConnection();
            preparedStatement = connection.prepareStatement(SQLRequest.GET_ORDER);
            preparedStatement.setInt(1,id);
            resultProduct = preparedStatement.executeQuery();
            while (resultProduct.next()) {
                product = initProductForUser(resultProduct);
                list.add(product);
            }
        } catch (SQLException e) {
            logger.error("SQLException is thrown when trying " +SQLRequest.GET_ORDER,e);
            throw new DatabaseException("SQLException is thrown when trying" + SQLRequest.GET_PRODUCT, e);
        } catch (MySQLException e) {
            logger.error("MySQLDBDaoException is thrown when trying to take connection", e);
            throw new DatabaseException("MySQLDBDaoException is thrown when trying to take connection", e);
        } finally {
            try {
                if (preparedStatement != null) {
                    if (!preparedStatement.isClosed()) {
                        preparedStatement.close();
                    }
                }
                if (connection != null) {
                    poolConnections.putConnection(connection);
                }
            } catch (MySQLException e) {
                logger.error("MySQLDaoException is thrown when trying to put connection ", e);
                throw new DatabaseException("MySQLDBDaoException is thrown when trying to put connection ", e);
            } catch (SQLException e) {
                logger.error("SQLException is thrown when closing a statement", e);
                throw new DatabaseException("SQLException is thrown when closing a statement or ResultSet", e);
            }
        }
        return list;
    }
    private Product initProductForUser(ResultSet resultSet) throws SQLException {
        Product product = new Product();
        product.setId_product(resultSet.getLong(DBColumnName.ID_PRODUCT));
        product.setAmount(resultSet.getLong(DBColumnName.AMOUNT));
        product.setDescription(resultSet.getString(DBColumnName.DESCRIPTION));
        product.setPrice(resultSet.getInt(DBColumnName.PRICE));
        product.setUserCount(resultSet.getInt(DBColumnName.COUNT));
        return product;
    }


    @Override
    public void addProduct (String description,int price,int amount) throws DatabaseException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = poolConnections.getConnection();
            preparedStatement = connection.prepareStatement(SQLRequest.ADD_PRODUCT);
            preparedStatement.setLong(1, Generator.generatorId());
            preparedStatement.setString(2, description);
            preparedStatement.setInt(3, price);
            preparedStatement.setInt(4, amount);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            logger.error("SQLException is thrown when trying " + SQLRequest.ADD_PRODUCT,e);
            throw new DatabaseException("SQLException is thrown when trying" + SQLRequest.ADD_TO_BASKET, e);
        } catch (MySQLException e) {
            logger.error("MySQLDBDaoException is thrown when trying to take connection", e);
            throw new DatabaseException("MySQLDBDaoException is thrown when trying to take connection", e);
        } finally {
            try {
                if (preparedStatement != null) {
                    if (!preparedStatement.isClosed()) {
                        preparedStatement.close();
                    }
                }
                if (connection != null) {
                    poolConnections.putConnection(connection);
                }
            } catch (MySQLException e) {
                logger.error("MySQLDaoException is thrown when trying to put connection ", e);
                throw new DatabaseException("MySQLDBDaoException is thrown when trying to put connection ", e);
            } catch (SQLException e) {
                logger.error("SQLException is thrown when closing a statement", e);
                throw new DatabaseException("SQLException is thrown when closing a statement or ResultSet", e);
            }
        }
    }

}
