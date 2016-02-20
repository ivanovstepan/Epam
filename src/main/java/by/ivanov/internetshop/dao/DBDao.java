package by.ivanov.internetshop.dao;

import by.ivanov.internetshop.entity.Product;
import by.ivanov.internetshop.entity.User;

import java.util.List;
import java.util.Map;

/**
 * Created by Stepan on 05.02.2016.
 */
public interface DBDao {
    public void addUser(User user);
    public User findUser(String login, String password);
    public List<Product> getProduct();
    public void addOrder(User user,Map<String,String[]> map);
    public List<User> getUsers();
    public void blockUser(String id,String description, int idAdmin);
}
