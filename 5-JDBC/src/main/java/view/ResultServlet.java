package view;

import database.*;
import model.Model;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Set;

/**
 * The main class of the servlet that handles printing out results of the text analysis given in collections - if
 * the session is valid otherwise, the index page is opened
 *
 * @author Hanna Gościniak
 * @version 1.0
 */
@WebServlet("/results")
public class ResultServlet extends HttpServlet {

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doGet(request, response);
    }

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession(false);
        PrintWriter out = response.getWriter();
        if (session == null) {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
            out.println("<font color=red>Session timeout!</font>");
            rd.include(request, response);
        } else {
            Model model = (Model) session.getAttribute("model");
            Connection connection = (Connection) session.getAttribute("connection");
            int id = (int) session.getAttribute("id");
            request.getRequestDispatcher("/view.html").include(request, response);
            out.println("<CENTER>");
            printTextInfo(model, out);
            try {
                printLastChar(model, out, connection, id);
                printWordsInfo(model, out, connection, id);
                printPalindromes(model, out, connection, id);
                printDiacritics(model, out, connection, id);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            out.println("</CENTER>");
            out.close();
        }
    }

    /**
     * Private method printing out information about text: string, words, number of chars and number of words.
     *
     * @param model an object of Model class
     * @param out   PrintWriter object
     */
    private void printTextInfo(Model model, PrintWriter out) {
        out.println("<H2>TEXT INFO</H2>");
        out.println("String: " + model.getString() + "<BR>");
        out.print("Words: ");
        model.getWords().forEach(p -> out.print(p + " "));
        out.println("<BR>");
        out.println("Number of chars: " + model.getNumberOfChars() + "<BR>");
        out.println("Number of words: " + model.getNumberOfWords() + "<BR>");
        out.println("<HR>");
    }

    /**
     * Private method printing out information about the last character of given text. It displays information
     * about the number of times the last character is repeated in this text, along with information about the
     * percentage of these letters.
     *
     * @param model an object of Model class
     * @param out   PrintWriter object
     * @param connection connection to database
     * @param id key needed for sql query
     * @throws SQLException in case any SQL error occurs
     */
    private void printLastChar(Model model, PrintWriter out, Connection connection, int id) throws SQLException {
        Statistics division = (a, b) -> setPrecision(((double) a * 100) / b);
        out.println("<H2>LAST CHARACTER</H2>");
        out.println("Last char, which is: " + model.getString().charAt(model.getString().length() - 1) + ", ");
        String s;
        if (model.countLastChar() > 1) {
            s = "s. ";
        } else s = ". ";
        out.println("occurs " + model.countLastChar() + " time" + s + "<BR>");
        out.println("It is " + divide(model.countLastChar(), model.getNumberOfChars(), division) + "% of all chars.");
        out.println("<HR>");
        //LastCharCRUD.update(connection, id, model.getString().charAt(model.getString().length() - 1), model.countLastChar());
        LastCharCRUD.create(connection, id, model.getString().charAt(model.getString().length() - 1), model.countLastChar());
    }

    /**
     * Private method printing out information about number of words in given text as well as displaying information
     * on how many words of a certain length are present in the text with a percentage value.
     *
     * @param model an object of Model class
     * @param out   PrintWriter object
     * @param connection connection to database
     * @param id key needed for sql query
     * @throws SQLException in case any SQL error occurs
     */
    private void printWordsInfo(Model model, PrintWriter out, Connection connection, int id) throws SQLException {
        Statistics division = (a, b) -> setPrecision(((double) a * 100) / b);
        out.println("<H2>WORDS INFO</H2>");
        out.println("Number of words: " + model.getNumberOfWords() + "<BR>");
        Set<Integer> setKeyLetters = model.getLetters().keySet();
        String s;
        if (model.getNumberOfWords() > 1) {
            s = "are:";
        } else s = "is:";
        out.println(" In the text there " + s + "<BR><BR>");
        for (Integer l : setKeyLetters) {
            String s1;
            if (model.getLetters().get(l) > 1) {
                s1 = "s. ";
            } else s1 = ". ";
            out.println("    " + model.getLetters().get(l) + " " + l + "-letter word" + s1 + "It is " + divide(model.getLetters().get(l), model.getNumberOfWords(), division) + "% of all words.<BR>");
            WordsCRUD.create(connection, id, l + "-letter", model.getLetters().get(l));
        }
        out.println("<HR>");
    }

    /**
     * Private method printing out information about palindromes. It counts and displays information about the number of
     * palindromes; if a given palindrome occurs more than 1 time, provide occurrence statistics for all such palindromes.
     * As a extension to functionality also palindromes, which occur 1 time are printed out.
     *
     * @param model an object of Model class
     * @param out   PrintWriter object
     * @param connection connection to database
     * @param id key needed for sql query
     * @throws SQLException in case any SQL error occurs
     */
    private void printPalindromes(Model model, PrintWriter out, Connection connection, int id) throws SQLException {
        Statistics division = (a, b) -> setPrecision(((double) a * 100) / b);
        out.println("<H2>PALINDROMES</H2>");
        if (model.getPalindromes().isEmpty()) {
            out.println("There is no palindromes!<BR>");
        } else {
            Set<String> setKeyPalindromes = model.getPalindromes().keySet();
            int count = 0;
            for (String l : setKeyPalindromes) {
                count += model.getPalindromes().get(l);
            }
            out.println("Number of palindromes: " + count + "<BR>");
            out.println("It is " + setPrecision(((double) count * 100) / model.getNumberOfWords()) + "% of all words.<BR><BR>");
            for (String l : setKeyPalindromes) {
                String s;
                if (model.getPalindromes().get(l) > 1) {
                    s = "s. ";
                } else s = ". ";
                out.println(l + " occurs " + model.getPalindromes().get(l) + " time" + s + "It is " + divide(model.getPalindromes().get(l), count, division) + "% of all palindromes and " + divide(model.getPalindromes().get(l), model.getNumberOfWords(), division) + "% of all words.<BR>");
                PalindromesCRUD.create(connection, id, l, model.getPalindromes().get(l));
            }
        }
        out.println("<HR>");
    }

    /**
     * Private method printing out information about diacritical marks. It displays number of diacritics along with
     * information about the percentage of each diacritics in the text.
     *
     * @param model an object of Model class
     * @param out   PrintWriter object
     * @param connection connection to database
     * @param id key needed for sql query
     * @throws SQLException in case any SQL error occurs
     */
    private void printDiacritics(Model model, PrintWriter out, Connection connection, int id) throws SQLException {
        Statistics division = (a, b) -> setPrecision(((double) a * 100) / b);
        out.println("<H2>DIACRITICAL MARKS</H2>");
        if (model.getDiacritics().isEmpty()) {
            out.println("There is no diacritical marks!<BR>");
        } else {
            Set<Character> setKeyDiacritics = model.getDiacritics().keySet();
            int count = 0;
            for (Character l : setKeyDiacritics) {
                count += model.getDiacritics().get(l);
            }
            out.println("Number of diacritical marks: " + count + "<BR>");
            out.println("It is " + divide(count, model.getNumberOfChars(), division) + "% of all chars.<BR><BR>");
            for (Character l : setKeyDiacritics) {
                String s;
                if (model.getDiacritics().get(l) > 1) {
                    s = "s";
                } else {
                    s = "";
                }
                out.println("    " + l + " occurs " + model.getDiacritics().get(l) + " time" + s + ". It is " + divide(model.getDiacritics().get(l), count, division) + "% of all diacritics and " + divide(model.getDiacritics().get(l), model.getNumberOfChars(), division) + "% of all chars.<BR>");
                DiacriticsCRUD.create(connection, id, l, model.getDiacritics().get(l));
            }
        }
        out.println("<HR>");
    }

    /**
     * Private method setting precision of given value to two decimal places.
     *
     * @param value double type value that is going to be rounded
     * @return rounded value
     */
    private double setPrecision(double value) {
        return BigDecimal.valueOf(value).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    /**
     * Private method with lambda expression as a parameter
     *
     * @param a  dividend
     * @param b  divisor
     * @param op lambda expression
     * @return result of operation
     */
    double divide(int a, int b, Statistics op) {
        return op.operation(a, b);
    }

    /**
     * Specification of the lambda expression with two parameters using interface
     */
    interface Statistics {
        double operation(int a, int b);
    }
}
