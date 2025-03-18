package krek.tMCORE.Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {

  // if you move every data loading and saving in here
  // the main file will be cleared a lot , and tthats a good thing
  // if you do it more early than any other implementation , its gonna be very lovely
  // thank you future me : 3

    // just follow the video yea ? https://www.youtube.com/watch?v=lF_a8H679OI

    private final Connection connection;

    public Database(String path) throws SQLException
    {
        connection = DriverManager.getConnection("jdbc:sqlite:" + path);
        Statement statement = connection.createStatement();
        statement.execute("""
            CREATE TABLE IF NOT EXISTS player (
                uuid TEXT PRIMARY KEY,
                username TEXT NOT NULL
            )
        """);
        statement.close();
    }

    public void closeConnection() throws SQLException {
        if (connection != null && !connection.isClosed())
        {
            connection.close();
        }
    }

}