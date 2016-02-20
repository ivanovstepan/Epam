package by.ivanov.internetshop.dao;


import by.ivanov.internetshop.dao.util.DBColumnName;
import by.ivanov.internetshop.dao.util.SQLRequest;
import by.ivanov.internetshop.entity.Product;
import by.ivanov.internetshop.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MySQLDBDao implements DBDao {
    private static MySQLDBDao instance;
    private volatile static boolean instanceCreated = false;
    private static MySQLDBPoolConnection poolConnections;


    public static MySQLDBDao getInstance() {
        if (!instanceCreated) {
            instance = new MySQLDBDao();
            try {
                poolConnections = new MySQLDBPoolConnection();
            } catch (Exception e) {
                e.printStackTrace();
            }
            instanceCreated = true;
        }
        return instance;
    }

    public static void init() throws Exception {
        poolConnections = new MySQLDBPoolConnection();
    }

    public static void destroy() throws SQLException {
        poolConnections.destroy();
    }

    public void addUser(User user) {
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
            // throw new SQLException("SQLException is thrown when trying" + SQLRequest.ADD_USER, e);
        } catch (Exception e) {
            e.printStackTrace();
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
            } catch (SQLException e) {
                // throw new SQLException("SQLException is thrown when trying" + SQLRequest.ADD_USER, e);
            }
        }
    }

    @Override
    public User findUser(String login, String password) {
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
            e.printStackTrace();
        } finally {
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


            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
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
        product.setAmount(resultSet.getInt(DBColumnName.AMOUNT));
        product.setDescription(resultSet.getString(DBColumnName.DESCRIPTION));
        product.setPrice(resultSet.getInt(DBColumnName.PRICE));
        return product;
    }

    @Override
    public List<Product> getProduct() {
        List<Product> list = new ArrayList<Product>();
        Product product = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultProduct = null;
        try {
            connection = poolConnections.getConnection();
            preparedStatement = connection.prepareStatement(SQLRequest.GET_PRODUCT);
            resultProduct = preparedStatement.executeQuery();
            while (resultProduct.next()) {
                product = initProduct(resultProduct);
                list.add(product);
            }
        } catch (SQLException e) {
            // throw new SQLException("SQLException is thrown when trying" + SQLRequest.ADD_USER, e);
        } catch (Exception e) {
            e.printStackTrace();
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
            } catch (SQLException e) {
                // throw new SQLException("SQLException is thrown when trying" + SQLRequest.ADD_USER, e);
            }
        }
        return list;
    }
    @Override
    public void addOrder(User user,Map<String,String[]> map) {
        String[] idPoduct = map.get("product");
        String[] count = map.get("count");

        for (int i = 0; i < idPoduct.length; i++) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
            try {
                connection = poolConnections.getConnection();
                preparedStatement = connection.prepareStatement(SQLRequest.ADD_TO_BASKET);
                preparedStatement.setLong(1, Long.parseLong(idPoduct[i]));
                preparedStatement.setLong(2, user.getId());
                preparedStatement.setInt(3, Integer.parseInt(count[i]));
//               preparedStatement.executeUpdate();
                System.out.print(preparedStatement.executeUpdate());
            } catch (SQLException e) {
                // throw new SQLException("SQLException is thrown when trying" + SQLRequest.ADD_USER, e);
            } catch (Exception e) {
                e.printStackTrace();
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
                } catch (SQLException e) {
                    // throw new SQLException("SQLException is thrown when trying" + SQLRequest.ADD_USER, e);
                }
            }
        }
    }

    @Override
    public List<User> getUsers() {
        List<User> list = new ArrayList<User>();
        User user = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultProduct = null;
        try {
            connection = poolConnections.getConnection();
            preparedStatement = connection.prepareStatement(SQLRequest.GET_USERS);
            resultProduct = preparedStatement.executeQuery();
            while (resultProduct.next()) {
                user = initUser(resultProduct);
                list.add(user);
            }
        } catch (SQLException e) {
            // throw new SQLException("SQLException is thrown when trying" + SQLRequest.ADD_USER, e);
        } catch (Exception e) {
            e.printStackTrace();
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
            } catch (SQLException e) {
                // throw new SQLException("SQLException is thrown when trying" + SQLRequest.ADD_USER, e);
            }
        }
        return list;
    }


    @Override
    public void blockUser(String id,String description, int idAdmin) {

            Connection connection = null;
            PreparedStatement preparedStatement = null;
            try {
                connection = poolConnections.getConnection();
                preparedStatement = connection.prepareStatement(SQLRequest.BLOCKUSER);
                preparedStatement.setLong(1, Long.parseLong(id));
                preparedStatement.setLong(2, idAdmin);
                preparedStatement.setString(3, description);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                // throw new SQLException("SQLException is thrown when trying" + SQLRequest.ADD_USER, e);
            } catch (Exception e) {
                e.printStackTrace();
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
                } catch (SQLException e) {
                    // throw new SQLException("SQLException is thrown when trying" + SQLRequest.ADD_USER, e);
                }
            }
        }


}

