package by.ivanov.internetshop.servlet;

import by.ivanov.internetshop.command.Command;
import by.ivanov.internetshop.command.CommandFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class Controller extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String page = null;
        request.setCharacterEncoding("utf-8");
        String commandName = request.getParameter(ParameterName.COMMAND);
        CommandFactory commandFactory = CommandFactory.getInstance();
        Command command = commandFactory.createCommand(commandName);

        page = command.execute(request);
        sendResult(request, response, page);
    }
    private void sendResult(HttpServletRequest request, HttpServletResponse response, String page) throws IOException, ServletException {
        if (page == null) {
            response.sendRedirect(NameJspPage.INDEX_PAGE);
        } else {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
            if (dispatcher != null) {
                dispatcher.forward(request, response);
            } else {
                errorMessageDireclyFromResponse(response);
            }
        }
    }

    private void errorMessageDireclyFromResponse(HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        response.getWriter().println("E R R O R !!!");
    }

}
