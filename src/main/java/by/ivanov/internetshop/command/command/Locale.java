package by.ivanov.internetshop.command.command;


import by.ivanov.internetshop.command.Command;

import javax.servlet.http.HttpServletRequest;

public class Locale implements Command {
    private static final String PARAMETER_NAME_CURRENT_URL = "currentUrl";
    private static final String DEFAULT_LOCALE = "en";
    @Override
    public String execute(HttpServletRequest request) {
        String current = request.getParameter("locale");
        if ( current == null){
            current = DEFAULT_LOCALE;
        }
        request.getSession().setAttribute("locale", current);
        return request.getParameter(PARAMETER_NAME_CURRENT_URL);
    }
}
