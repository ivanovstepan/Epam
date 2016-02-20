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

public class Register implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession(true);

            String  name = request.getParameter(ParameterName.NAME);
            String password = request.getParameter(ParameterName.PASSWORD);
            String login = request.getParameter(ParameterName.LOGIN);
            String surname = request.getParameter(ParameterName.SURNAME);
            String email = request.getParameter(ParameterName.EMAIL);
            User user = new User(name,surname,login,password,email,"user");
           //нужна проверка на адекватность данных
            session.setAttribute("user",user);
//          request.getSession().setAttribute("user",entity);

        DaoFactory daoFactory = DaoFactory.getInstance();
        MySQLDBDao mySQLDBDao = daoFactory.getSQLDBDao();
        List<Product> productList = mySQLDBDao.getProduct();
        session.setAttribute("product",productList);
        mySQLDBDao.addUser(user);

        return NameJspPage.TRY_PAGE;
    }







}
