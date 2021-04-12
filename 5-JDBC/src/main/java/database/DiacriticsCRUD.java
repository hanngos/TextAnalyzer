package database;

import java.io.PrintWriter;
import java.sql.*;

/**
 * Class used for implementation of CRUD methods for Diacritics table
 *
 * @author Hanna Gościniak
 * @version 1.0
 */
public class DiacriticsCRUD {

    /**
     * Public static method to execute sql insert query
     * @param connection connection to database
     * @param id parameter needed to execute aforementioned query (key)
     * @param diacritic parameter needed to execute aforementioned query
     * @param occurrence parameter needed to execute aforementioned query
     * @throws SQLException if an SQL error occurs
     */
    public static void create(Connection connection, int id, char diacritic, int occurrence) throws SQLException {
        try {
            String sql = "insert into \"Diacritics\" values (?,?,?)";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.setString(2, String.valueOf(diacritic));
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
            String sql = "select * from \"Diacritics\"";

            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);

            out.println("<table class=center style=\"width:30%\"><tr><th>ID</th><th>DIACRITIC</th><th>OCCURRENCE</th></tr>");

            while (result.next()){
                int id = result.getInt(1);
                String diacritic = result.getString(2);
                int occurrence = result.getInt(3);

                out.println("<tr><td>"+id +"</td><td>" + diacritic +"</td><td>" + occurrence +"</td></tr>");
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
     * @param diacritic parameter needed to execute aforementioned query
     * @param occurrence parameter needed to execute aforementioned query
     * @throws SQLException if an SQL error occurs
     */
    public static void update(Connection connection, int id, char diacritic, int occurrence) throws SQLException {
        try {
            String sql = "update \"Diacritics\" set DIACRITIC = ?, OCCURRENCE = ? where ID =" + id;

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, String.valueOf(diacritic));
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
            String sql = "delete from \"Diacritics\" where ID = ?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);

            statement.executeUpdate();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

}
