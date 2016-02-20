package by.ivanov.internetshop.command.command;

import by.ivanov.internetshop.command.Command;
import by.ivanov.internetshop.dao.DaoFactory;
import by.ivanov.internetshop.dao.MySQLDBDao;
import by.ivanov.internetshop.entity.User;
import by.ivanov.internetshop.servlet.NameJspPage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public class BlockUser implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        String id  = request.getParameter("block");
        String description = request.getParameter("description");
        User admin = (User)request.getSession().getAttribute("user");
        int idAdmin = admin.getId();

        DaoFactory daoFactory = DaoFactory.getInstance();
        MySQLDBDao mySQLDBDao = daoFactory.getSQLDBDao();

        mySQLDBDao.blockUser(id,description,idAdmin);

        return NameJspPage.ADMIN_PAGE;
    }
}
