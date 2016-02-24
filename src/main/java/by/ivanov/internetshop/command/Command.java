package by.ivanov.internetshop.command;


import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public interface Command {
    public final static Logger logger = Logger.getRootLogger();

    String execute(HttpServletRequest request) throws CommandException;
}
