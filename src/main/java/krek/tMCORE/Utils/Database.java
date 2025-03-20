package krek.tMCORE.Utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.stream.Collectors;

public class Database {

  // if you move every data loading and saving in here
  // the main file will be cleared a lot , and tthats a good thing
  // if you do it more early than any other implementation , its gonna be very lovely
  // thank you future me : 3

    // just follow the video yea ? https://www.youtube.com/watch?v=lF_a8H679OI

    private final Connection connection;

    public String readSqlFile(String filePath) throws IOException {
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(new File(filePath), StandardCharsets.UTF_8))
            {
                return reader.readLine().lines().collect(Collectors.joining("\n"));
            };
        }catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public Database(String path) throws SQLException, IOException {
        connection = DriverManager.getConnection("jdbc:sqlite:" + path);
        Statement statement = connection.createStatement();

        /*

        statement.execute("""
            CREATE TABLE IF NOT EXISTS player (
                uuid TEXT PRIMARY KEY,
                username TEXT NOT NULL
            )
        """);

         */

        // this is very good idea I think , but idk how to make it better

        statement.execute(readSqlFile("krek/tMCORE/raw/init.sql"));

        statement.close();
    }

    public void closeConnection() throws SQLException {
        if (connection != null && !connection.isClosed())
        {
            connection.close();
        }
    }

}