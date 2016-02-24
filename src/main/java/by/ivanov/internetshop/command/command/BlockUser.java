package by.ivanov.internetshop.command.command;

import by.ivanov.internetshop.command.Command;
import by.ivanov.internetshop.command.CommandException;
import by.ivanov.internetshop.dao.DaoFactory;
import by.ivanov.internetshop.dao.DatabaseException;
import by.ivanov.internetshop.dao.mysql.MySQLDao;
import by.ivanov.internetshop.entity.User;
import by.ivanov.internetshop.servlet.NameJspPage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;


public class BlockUser implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession(true);
        Map<String,String[]> map = request.getParameterMap();
        User user = (User)request.getSession().getAttribute("user");
        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            MySQLDao SQLDao = daoFactory.getSQLDBDao();

            SQLDao.blockUser(map,user.getId());


        }
        catch (DatabaseException e) {
            throw new CommandException("DatabaseException is thrown", e);
        }
        return NameJspPage.ERROR_PAGE;
    }
}
