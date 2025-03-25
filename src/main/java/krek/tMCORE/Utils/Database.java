package krek.tMCORE.Utils;

import krek.tMCORE.Statistics.PlayerStats;
import krek.tMCORE.TMCORE;
import org.bukkit.entity.Player;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.util.stream.Collectors;

public class Database {
    private final Connection connection;

    public Database(String path) throws SQLException, IOException {
        connection = DriverManager.getConnection("jdbc:sqlite:" + path);
        Statement statement = connection.createStatement();

        statement.execute("""
        CREATE TABLE IF NOT EXISTS players (
        uuid TEXT PRIMARY KEY,
        level INTEGER NOT NULL DEFAULT 1,
        xp INTEGER NOT NULL DEFAULT 0,
        vigor INTEGER NOT NULL DEFAULT 10,
        strength INTEGER NOT NULL DEFAULT 10,
        dexterity INTEGER NOT NULL DEFAULT 10,
        intelligence INTEGER NOT NULL DEFAULT 10
    )
""");

        statement.close();
    }

    public void addPlayer(Player p) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement
                ("INSERT INTO players (uuid, Level, Xp, Vigor, Strength, Dexterity, Intelligence) VALUES (?, ?, ? ,? ,? ,? ,?)"))
        {
            PlayerStats stats = TMCORE.getPlayerStats(p);
            System.out.println("Saving PlayerStats:");
            System.out.println("Level: " + stats.getLevel());
            System.out.println("XP: " + stats.getXp());
            System.out.println("Vigor: " + stats.getVigor());
            System.out.println("Strength: " + stats.getStrength());
            System.out.println("Dexterity: " + stats.getDexterity());
            System.out.println("Intelligence: " + stats.getIntelligence());
            preparedStatement.setString(1, p.getUniqueId().toString());
            preparedStatement.setInt(2, stats.getLevel());
            preparedStatement.setInt(3, stats.getXp());
            preparedStatement.setInt(4, stats.getVigor());
            preparedStatement.setInt(5, stats.getStrength());
            preparedStatement.setInt(6, stats.getDexterity());
            preparedStatement.setInt(7, stats.getIntelligence());
            preparedStatement.executeUpdate();
        }

    }

    public void closeConnection() throws SQLException {
        if (connection != null && !connection.isClosed())
        {
            connection.close();
        }
    }

}