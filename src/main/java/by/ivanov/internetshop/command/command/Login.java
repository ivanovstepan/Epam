package by.ivanov.internetshop.command.command;

import by.ivanov.internetshop.command.Command;
import by.ivanov.internetshop.dao.DaoFactory;
import by.ivanov.internetshop.dao.MySQLDBDao;
import by.ivanov.internetshop.entity.Product;
import by.ivanov.internetshop.entity.User;
import by.ivanov.internetshop.servlet.NameJspPage;
import by.ivanov.internetshop.servlet.ParameterName;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class Login implements Command {

    @Override
    public String execute(HttpServletRequest request) {

        HttpSession session = request.getSession(true);
        String login = request.getParameter(ParameterName.LOGIN);
        String password = request.getParameter(ParameterName.PASSWORD);
        User user;
        String page = null;
        DaoFactory daoFactory = DaoFactory.getInstance();
        MySQLDBDao mySQLDBDao = daoFactory.getSQLDBDao();

        user = mySQLDBDao.findUser(login, password);
        List<Product> productList = mySQLDBDao.getProduct();
        session.setAttribute("product",productList);



            if (user != null){
                session.setAttribute("user", user);
                if(user.getRole().equals("user"))

                    page = NameJspPage.TRY_PAGE;

                else {
                    List<User> userList = mySQLDBDao.getUsers();
                    session.setAttribute("users", userList);
                    page = NameJspPage.ADMIN_PAGE;

                }
            }
            else
            page = NameJspPage.ERROR_PAGE;

        return page;
    }
}
