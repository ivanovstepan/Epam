package by.ivanov.internetshop.command.command;

import by.ivanov.internetshop.command.Command;
import by.ivanov.internetshop.dao.DaoFactory;
import by.ivanov.internetshop.dao.MySQLDBDao;
import by.ivanov.internetshop.entity.User;
import by.ivanov.internetshop.servlet.NameJspPage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;


public class AddToBasket implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        User user = (User) session.getAttribute("user");
        Map<String,String[]> map = request.getParameterMap();

        DaoFactory daoFactory = DaoFactory.getInstance();
        MySQLDBDao mySQLDBDao = daoFactory.getSQLDBDao();

        mySQLDBDao.addOrder(user,map);
        return NameJspPage.TRY_PAGE;
    }
}
