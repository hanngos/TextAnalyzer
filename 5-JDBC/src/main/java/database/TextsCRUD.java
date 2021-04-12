package database;

import model.Model;

import java.io.PrintWriter;
import java.sql.*;

/**
 * Class used for implementation of CRUD methods for Texts table
 *
 * @author Hanna Gościniak
 * @version 1.0
 */
public class TextsCRUD {

    /**
     * Public static method to execute sql insert query
     * @return id (primary key)
     * @param connection connection to database
     * @param model object of Model Class
     * @throws SQLException if an SQL error occurs
     */
    public static int create(Connection connection, Model model) throws SQLException {
        int id = 0;
        try {
            String sql = "insert into \"Texts\" (TEXT) values  (?)";

            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, model.getString());

            statement.executeUpdate();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    id = (int) generatedKeys.getLong(1);
                    InfoCRUD.create(connection, id, model.getNumberOfChars(), model.getNumberOfWords());
                    id = (int) generatedKeys.getLong(1);
                }
                else {
                    throw new SQLException("Creating info text failed, no ID obtained.");
                }
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return id;
    }

    /**
     * Public static method to execute sql select query
     * @param connection connection to database
     * @param out PrintWriter object
     * @throws SQLException if an SQL error occurs
     */
    public static void select(Connection connection, PrintWriter out) throws SQLException {
        try {
            String sql = "select * from \"Texts\"";

            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);

            out.println("<table class=center style=\"width:30%\"><tr><th>ID</th><th>TEXT</th></tr>");

            while (result.next()){
                int id = result.getInt(1);
                String text = result.getString(2);

                out.println("<tr><td>"+id +"</td><td>" + text +"</td></tr>");
            }
            out.println("</table>");
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Public static method to execute sql update query
     * @param connection connection to database
     * @param id parameter needed to execute aforementioned query (key)
     * @param text parameter needed to execute aforementioned query
     * @throws SQLException if an SQL error occurs
     */
    public static void update(Connection connection, int id, String text) throws SQLException {
        try {
            String sql = "update \"Texts\" set TEXT = ? where ID =" + id;

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, text);

            statement.executeUpdate();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Public static method to execute sql delete query
     * @param connection connection to database
     * @param id parameter needed to execute aforementioned query (key)
     * @throws SQLException if an SQL error occurs
     */
    public static void delete(Connection connection, int id) throws SQLException {
        try {
            String sql = "delete from \"Texts\" where ID = ?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);

            statement.executeUpdate();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

}
