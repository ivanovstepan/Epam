package by.ivanov.internetshop.command.command;

import by.ivanov.internetshop.command.Command;
import by.ivanov.internetshop.servlet.NameJspPage;

import javax.servlet.http.HttpServletRequest;

public class Logout implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        request.getSession(true).invalidate();
        return NameJspPage.LOGIN_PAGE;
    }
}
