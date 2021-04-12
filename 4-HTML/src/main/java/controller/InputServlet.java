package controller;

import model.Model;
import model.NoInputTextException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * The main class of the servlet that handles input given by user - if there was no such - exception is thrown
 * otherwise, calculations proceed
 *
 * @author Hanna Gościniak
 * @version 1.0
 */
@WebServlet("/input")
public class InputServlet extends HttpServlet {

    /**
     * An object of Model class
     */
    Model model = new Model();

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws IOException if an I/O error occurs
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doPost(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws IOException if an I/O error occurs
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        HttpSession session = request.getSession(true);
        PrintWriter out;
        out = response.getWriter();
        out.println("<HTML><HEAD><TITLE>Input Text");
        out.println("</TITLE></HEAD><BODY><CENTER>");
        String string = request.getParameter("inputText");
        model.clear();
        try {
            if (string.isBlank() || string.isEmpty())
                throw new NoInputTextException();
            else {
                model.setFields(string);
                out.println("<H2>This is text you typed in: </H2><P STYLE=\"FONT-SIZE:25px\">" + model.getString() + "</P>");
                session.setAttribute("model", model);
                Cookie[] cookies = request.getCookies();
                for (Cookie c : cookies) {
                    if (c.getName().equals("text")) {
                        int val = Integer.parseInt(c.getValue()) + 1;
                        c.setMaxAge(0);
                        Cookie cookie = new Cookie("text", Integer.toString(val));
                        response.addCookie(cookie);
                        out.println("This is " + cookie.getValue() + ". " + cookie.getName() + " this program analyzed!<BR><BR>");
                        break;
                    } else {
                        Cookie cookie = new Cookie("text", "1");
                        response.addCookie(cookie);
                    }
                }
                out.println("<FORM ACTION=\"/TextAnalyzer_war_exploded/results\" method=\"GET\" >");
                out.println("<input type=\"submit\" name=\"button\" value=\"SEE RESULTS\"></FORM>");
            }
        } catch (NoInputTextException e) {
            out.println("<H2>" + e.getMessage() + "</H2>");
        }
        out.println("<FORM ACTION=\"/TextAnalyzer_war_exploded/index.jsp\" >");
        out.println("<input type=\"submit\" name=\"button\" value=\"NEW\"></FORM>");
        out.println("</CENTER></BODY></HTML>");
        out.close();

    }
}

