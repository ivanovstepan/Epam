package by.ivanov.internetshop.dao;

import by.ivanov.internetshop.entity.Product;
import by.ivanov.internetshop.entity.User;

import java.util.List;
import java.util.Map;

/**
 * Created by Stepan on 05.02.2016.
 */
public interface DBDao {
    public void addUser(User user) throws DatabaseException;
    public User findUser(String login, String password) throws DatabaseException;
    public List<Product> getProduct() throws DatabaseException;
    public void addOrder(User user,Map<String,String[]> map) throws DatabaseException;
    public List<User> getUsers() throws DatabaseException;
    public void blockUser(Map<String,String[]> map, int idAdmin) throws DatabaseException;
    public List <Product> getOrder (int id) throws DatabaseException;
    public void addProduct(String description,int price,int amount) throws DatabaseException;
}
