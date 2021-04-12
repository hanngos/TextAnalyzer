package database;

import java.io.PrintWriter;
import java.sql.*;

/**
 * Class used for implementation of CRUD methods for Words table
 *
 * @author Hanna Gościniak
 * @version 1.0
 */
public class WordsCRUD {

    /**
     * Public static method to execute sql insert query
     * @param connection connection to database
     * @param id parameter needed to execute aforementioned query (key)
     * @param letter parameter needed to execute aforementioned query
     * @param occurrence parameter needed to execute aforementioned query
     * @throws SQLException if an SQL error occurs
     */
    public static void create(Connection connection, int id, String letter, int occurrence) throws SQLException {
        try {
            String sql = "insert into \"Words\" values (?,?,?)";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.setString(2, letter);
            statement.setInt(3, occurrence);

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
            String sql = "select * from \"Words\"";

            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);

            out.println("<table class=center style=\"width:30%\"><tr><th>ID</th><th>LETTER</th><th>OCCURRENCE</th></tr>");

            while (result.next()){
                int id = result.getInt(1);
                String letter = result.getString(2);
                int occurrence = result.getInt(3);

                out.println("<tr><td>"+id +"</td><td>" + letter +"</td><td>" + occurrence +"</td></tr>");
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
     * @param letter parameter needed to execute aforementioned query
     * @param occurrence parameter needed to execute aforementioned query
     * @throws SQLException if an SQL error occurs
     */
    public static void update(Connection connection, int id, String letter, int occurrence) throws SQLException {
        try {
            String sql = "update \"Words\" set LETTER = ?, OCCURRENCE = ? where ID = " + id;

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, letter);
            statement.setInt(2, occurrence);

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
            String sql = "delete from \"Words\" where ID=";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);

            statement.executeUpdate();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

}
