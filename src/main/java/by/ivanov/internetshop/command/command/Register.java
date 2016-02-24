package by.ivanov.internetshop.command.command;

import by.ivanov.internetshop.command.Command;
import by.ivanov.internetshop.command.CommandException;
import by.ivanov.internetshop.dao.DaoFactory;
import by.ivanov.internetshop.dao.DatabaseException;
import by.ivanov.internetshop.dao.mysql.MySQLDao;
import by.ivanov.internetshop.entity.Product;
import by.ivanov.internetshop.entity.User;
import by.ivanov.internetshop.servlet.NameJspPage;
import by.ivanov.internetshop.servlet.ParameterName;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class Register implements Command {

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession(true);

        String name = request.getParameter(ParameterName.NAME);
        String password = request.getParameter(ParameterName.PASSWORD);
        String login = request.getParameter(ParameterName.LOGIN);
        String surname = request.getParameter(ParameterName.SURNAME);
        String email = request.getParameter(ParameterName.EMAIL);
        User user = new User(name, surname, login, password, email, "user");
        //нужна проверка на адекватность данных
        session.setAttribute("user", user);

        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            MySQLDao SQLDao = daoFactory.getSQLDBDao();
            List<Product> productList = SQLDao.getProduct();
            session.setAttribute("product", productList);
            SQLDao.addUser(user);
        }
        catch (DatabaseException e) {
            throw new CommandException("DatabaseException is thrown", e);
        }
        return NameJspPage.TRY_PAGE;
    }







}
