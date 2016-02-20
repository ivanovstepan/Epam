package by.ivanov.internetshop.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class MySQLDBPoolConnection {
    private String url = "jdbc:mysql://localhost:3306/internetshop";
    private String user = "root";
    private String password = "root";
    private String driver = "com.mysql.jdbc.Driver";
    private int size = 5;
    private BlockingQueue<Connection> connections;

    public MySQLDBPoolConnection() throws Exception {
        try {
            Class.forName(driver);
            connections = new ArrayBlockingQueue<Connection>(size);
            for (int i = 0; i < size; i++) {
                connections.add(DriverManager.getConnection(this.url, this.user, this.password));
            }
        }
        catch (SQLException e) {
            throw new SQLException("SQLException is thrown.", e);

        } catch (ClassNotFoundException e) {
            throw new Exception("ClassNotFoundException is thrown.", e);
        }

    }



    public Connection getConnection() throws SQLException {
        try {

            return connections.take();
        } catch (InterruptedException e) {
            throw new SQLException("InterruptedException is thrown.", e);
        }
    }

    public void putConnection(Connection connection)  throws SQLException {
        connections.offer(connection);
    }

    public void destroy() throws SQLException {
        for (Connection connection : connections) {
            if (connection != null) {
                if (!connection.isClosed()) {
                    connection.close();
                }
            }
        }
    }
}
