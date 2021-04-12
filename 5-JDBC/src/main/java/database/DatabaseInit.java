package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Class used to initialize the database connection and create tables
 *
 * @author Hanna Gościniak
 * @version 1.0
 */
public class DatabaseInit {

    /**
     * Public static method used to initialize all the information regarding database and its name to access
     * @return connection to database
     * @throws SQLException in case any SQL error occurs
     * @throws ClassNotFoundException if class was mot found
     */
    public static Connection initDatabase() throws SQLException, ClassNotFoundException
    {
        Class.forName("org.apache.derby.jdbc.ClientDriver");
        return DriverManager.getConnection("jdbc:derby://localhost:1527/database;create=true");
    }

    /**
     * Public static method to create all needed sql table
     * @param connection connection to database
     * @throws SQLException if an SQL error occurs
     */
    public static void createTables(Connection connection) throws SQLException {
        createTextsTable(connection);
        createInfoTable(connection);
        createLastCharTable(connection);
        createWordsTable(connection);
        createPalindromesTable(connection);
        createDiacriticsTable(connection);
    }

    /**
     * Private static method to create sql Texts table
     * @param connection connection to database
     * @throws SQLException if an SQL error occurs
     */
    private static void createTextsTable(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate("create table \"Texts\"(\n" +
                "        ID   INTEGER not null GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1)\n" +
                "            constraint TEXTS_PK\n" +
                "            primary key,\n" +
                "        TEXT VARCHAR(128)\n" +
                "    )");
    }

    /**
     * Private static method to create sql Info table
     * @param connection connection to database
     * @throws SQLException if an SQL error occurs
     */
    private static void createInfoTable(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate("create table \"Info\"(\n" +
                "        ID    INTEGER not null\n" +
                "            constraint INFO_PK\n" +
                "                primary key\n" +
                "            constraint INFO_FK\n" +
                "                references \"Texts\"\n" +
                "                on delete cascade,\n" +
                "        CHARS INTEGER not null,\n" +
                "        WORDS INTEGER not null\n" +
                "    )");
    }

    /**
     * Private static method to create sql LastChar table
     * @param connection connection to database
     * @throws SQLException if an SQL error occurs
     */
    private static void createLastCharTable(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate("create table \"LastChar\"(\n" +
                "                           ID         INTEGER    not null\n" +
                "                               constraint LASTCHAR_PK\n" +
                "                                   primary key\n" +
                "                               constraint LASTCHAR_FK\n" +
                "                                   references \"Texts\"\n" +
                "                                   on delete cascade,\n" +
                "                           LASTCHAR   VARCHAR(1) not null,\n" +
                "                           OCCURRENCE INTEGER    not null\n" +
                ")");
    }

    /**
     * Private static method to create sql Words table
     * @param connection connection to database
     * @throws SQLException if an SQL error occurs
     */
    private static void createWordsTable(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate("create table \"Words\"(\n" +
                "        ID         INTEGER      not null\n" +
                "            constraint WORDS_FK\n" +
                "                references \"Texts\"\n" +
                "                on delete cascade,\n" +
                "        LETTER     VARCHAR(128) not null,\n" +
                "        OCCURRENCE INTEGER      not null,\n" +
                "        constraint WORDS_PK\n" +
                "            primary key (ID, LETTER)\n" +
                "    )");
    }

    /**
     * Private static method to create sql Palindromes table
     * @param connection connection to database
     * @throws SQLException if an SQL error occurs
     */
    private static void createPalindromesTable(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate("create table \"Palindromes\"(\n" +
                "        ID         INTEGER      not null\n" +
                "            constraint PALINDROMES_FK\n" +
                "                references \"Texts\"\n" +
                "                on delete cascade,\n" +
                "        PALINDROME VARCHAR(128) not null,\n" +
                "        OCCURRENCE INTEGER      not null,\n" +
                "        constraint PALINDROMES_PK\n" +
                "            primary key (ID, PALINDROME)\n" +
                "    )");
    }

    /**
     * Private static method to create sql Diacritics table
     * @param connection connection to database
     * @throws SQLException if an SQL error occurs
     */
    private static void createDiacriticsTable(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate("create table \"Diacritics\"(\n" +
                "        ID         INTEGER    not null\n" +
                "            constraint DIACRITICS_FK\n" +
                "                references \"Texts\"\n" +
                "                on delete cascade,\n" +
                "        DIACRITIC  VARCHAR(1) not null,\n" +
                "        OCCURRENCE INTEGER    not null,\n" +
                "        constraint DIACRITICS_PK\n" +
                "            primary key (ID, DIACRITIC)\n" +
                "    )");
    }


}
