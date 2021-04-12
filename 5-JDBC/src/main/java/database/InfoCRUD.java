package database;

import java.io.PrintWriter;
import java.sql.*;

/**
 * Class used for implementation of CRUD methods for Info table
 *
 * @author Hanna Gościniak
 * @version 1.0
 */
public class InfoCRUD {

    /**
     * Public static method to execute sql insert query
     * @param connection connection to database
     * @param id parameter needed to execute aforementioned query (key)
     * @param chars parameter needed to execute aforementioned query
     * @param words parameter needed to execute aforementioned query
     * @throws SQLException if an SQL error occurs
     */
    public static void create(Connection connection, int id, int chars, int words) throws SQLException {
        try {
            String sql = "insert into \"Info\" values (?,?,?)";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.setInt(2, chars);
            statement.setInt(3, words);

            statement.executeUpdate();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Public static method to execute sql select query
     * @param connection connection to database
     * @param out PrintWriter object
     * @throws SQLException if an SQL error occurs
     */
    public static void select(Connection connection, PrintWriter out) throws SQLException {
        try {
            String sql = "select * from \"Info\"";

            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);

            out.println("<table class=center style=\"width:30%\"><tr><th>ID</th><th>CHARS</th><th>WORDS</th></tr>");

            while (result.next()){
                int id = result.getInt(1);
                int chars = result.getInt(2);
                int words = result.getInt(3);

                out.println("<tr><td>"+id +"</td><td>" + chars +"</td><td>" + words +"</td></tr>");
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
     * @param chars parameter needed to execute aforementioned query
     * @param words parameter needed to execute aforementioned query
     * @throws SQLException if an SQL error occurs
     */
    public static void update(Connection connection, int id, int chars, int words) throws SQLException {
        try {
            String sql = "update \"Info\" set CHARS = ?, WORDS = ?  where ID = " + id;

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, chars);
            statement.setInt(2, words);

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
            String sql = "delete from \"Info\" where ID = ?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);

            statement.executeUpdate();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

}
