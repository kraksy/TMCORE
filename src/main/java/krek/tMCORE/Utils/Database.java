package krek.tMCORE.Utils;

import krek.tMCORE.Statistics.PlayerStats;
import krek.tMCORE.TMCORE;
import krek.tMCORE.weapons.CustomWeapon;
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

        statement.execute("""
        CREATE TABLE IF NOT EXISTS weapons (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            name VARCHAR(100) NOT NULL,
            lore TEXT,
            item_visual VARCHAR(50) NOT NULL,
            ability VARCHAR(50),
            req_stats TEXT,
            has_special_effect BOOLEAN DEFAULT FALSE,
            created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
        )
        """);

        statement.execute("""
        CREATE TABLE IF NOT EXISTS enemies (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            name VARCHAR(100) NOT NULL,
            ability VARCHAR(50),
            stats TEXT,
            created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
        )
        """);

        statement.execute("""
        CREATE TABLE IF NOT EXISTS bosses (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            name VARCHAR(100) NOT NULL,
            ability VARCHAR(50),
            stats TEXT,
            created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
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

    public void getPlayerStatistics(Player p) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT Level, Xp, Vigor, Strength, Dexterity, Intelligence FROM players WHERE uuid = ?"
        )) {
            preparedStatement.setString(1, p.getUniqueId().toString());

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int level = resultSet.getInt("Level");
                    int xp = resultSet.getInt("Xp");
                    int vigor = resultSet.getInt("Vigor");
                    int strength = resultSet.getInt("Strength");
                    int dexterity = resultSet.getInt("Dexterity");
                    int intelligence = resultSet.getInt("Intelligence");

                    System.out.println("Loaded PlayerStats:");
                    System.out.println("Level: " + level);
                    System.out.println("XP: " + xp);
                    System.out.println("Vigor: " + vigor);
                    System.out.println("Strength: " + strength);
                    System.out.println("Dexterity: " + dexterity);
                    System.out.println("Intelligence: " + intelligence);
                } else {
                    System.out.println("No stats found for player " + p.getName());
                }
            }
        }
    }

    public void addWeapon(CustomWeapon weapon) throws SQLException
    {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO weapons (lore , item_visual, ability, req_Stats, has_special_effect, created_at) VALUES (?,?,?,?,?,?)"
        ))
        {
            preparedStatement.setString(1, weapon.getLore().toString());
            preparedStatement.v
        }
    }

    public void closeConnection() throws SQLException {
        if (connection != null && !connection.isClosed())
        {
            connection.close();
        }
    }

}