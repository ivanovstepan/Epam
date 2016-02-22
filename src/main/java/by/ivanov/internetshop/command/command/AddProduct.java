package by.ivanov.internetshop.command.command;

import by.ivanov.internetshop.command.Command;
import by.ivanov.internetshop.command.CommandException;
import by.ivanov.internetshop.dao.DaoFactory;
import by.ivanov.internetshop.dao.DatabaseException;
import by.ivanov.internetshop.dao.mysql.MySQLDBDao;
import by.ivanov.internetshop.dao.util.DBColumnName;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AddProduct implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession(true);
        String description = request.getParameter(DBColumnName.DESCRIPTION);
        int price = Integer.parseInt(request.getParameter(DBColumnName.PRICE));
        int amount = Integer.parseInt(request.getParameter(DBColumnName.AMOUNT));


        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            MySQLDBDao mySQLDBDao = daoFactory.getSQLDBDao();
            mySQLDBDao.addProduct(description,price,amount);

        } catch (DatabaseException e) {
                throw new CommandException("DatabaseException is thrown", e);
            }
        return "/jsp/new.jsp";
    }
}
