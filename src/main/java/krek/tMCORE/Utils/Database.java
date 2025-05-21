package krek.tMCORE.Utils;

import krek.tMCORE.Statistics.PlayerStats;
import krek.tMCORE.TMCORE;
import krek.tMCORE.weapons.CustomWeapon;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.sql.*;

public class Database {
    private final Connection connection;

    public Database(String path) throws SQLException, IOException {
        connection = DriverManager.getConnection("jdbc:sqlite:" + path);
        Statement statement = connection.createStatement();

        statement.execute("""
            CREATE TABLE IF NOT EXISTS playerStats (
            playerStatsId INTEGER PRIMARY KEY AUTOINCREMENT,
            vigor INTEGER NOT NULL DEFAULT 10,
            strength INTEGER NOT NULL DEFAULT 10,
            dexterity INTEGER NOT NULL DEFAULT 10,
            intelligence INTEGER NOT NULL DEFAULT 10
            )
        """);

        statement.execute("""
            CREATE TABLE IF NOT EXISTS players (
            uuid TEXT PRIMARY KEY,
            level INTEGER NOT NULL DEFAULT 1,
            xp INTEGER NOT NULL DEFAULT 0,
            playerStats INTEGER NOT NULL DEFAULT 0,
            FOREIGN KEY (playerStats) REFERENCES playerStats(playerStatsId)
            )
        """);

        statement.execute("""
        CREATE TABLE IF NOT EXISTS weapons (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            name TEXT NOT NULL,
            lore TEXT,
            item_visual TEXT NOT NULL,
            ability TEXT,
            created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
            playerStats INTEGER NOT NULL,
            FOREIGN KEY (playerStats) REFERENCES playerStats(playerStatsId)
        )
        """);

        statement.execute("""
        CREATE TABLE IF NOT EXISTS enemies (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            name TEXT NOT NULL,
            ability TEXT,
            stats TEXT,
            created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
        )
        """);

        statement.execute("""
        CREATE TABLE IF NOT EXISTS bosses (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            name TEXT NOT NULL,
            ability TEXT,
            stats TEXT,
            created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
        )
        """);

        statement.close();
    }

    public void addPlayer(Player p) throws SQLException {
        PlayerStats stats = TMCORE.getPlayerStats(p);
        connection.setAutoCommit(false);

        try (
                PreparedStatement statInsert = connection.prepareStatement(
                        "INSERT INTO playerStats (vigor, strength, dexterity, intelligence) VALUES (?, ?, ?, ?)",
                        Statement.RETURN_GENERATED_KEYS
                )
        ) {
            statInsert.setInt(1, stats.getVigor());
            statInsert.setInt(2, stats.getStrength());
            statInsert.setInt(3, stats.getDexterity());
            statInsert.setInt(4, stats.getIntelligence());
            statInsert.executeUpdate();

            int playerStatsId;
            try (ResultSet rs = statInsert.getGeneratedKeys()) {
                if (rs.next()) {
                    playerStatsId = rs.getInt(1);
                } else {
                    throw new SQLException("Failed to retrieve playerStatsId.");
                }
            }

            try (PreparedStatement playerInsert = connection.prepareStatement(
                    "INSERT INTO players (uuid, level, xp, playerStats) VALUES (?, ?, ?, ?)"
            )) {
                playerInsert.setString(1, p.getUniqueId().toString());
                playerInsert.setInt(2, stats.getLevel());
                playerInsert.setInt(3, stats.getXp());
                playerInsert.setInt(4, playerStatsId);
                playerInsert.executeUpdate();
            }

            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        } finally {
            connection.setAutoCommit(true);
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

    public void addWeapon(CustomWeapon weapon) throws SQLException {
        String weaponEntry = "INSERT INTO weapons (name, lore, item_visual, ability, created_at, playerStats) VALUES (?, ?, ?, ?, ?, ?)";
        String reqStatsEntry = "INSERT INTO playerStats(vigor, strength, dexterity, intelligence) VALUES (?, ?, ?, ?)";
        connection.setAutoCommit(false);

       PlayerStats req = weapon.getReqStats();

        try(PreparedStatement stats = connection.prepareStatement(reqStatsEntry,  Statement.RETURN_GENERATED_KEYS)) {
            stats.setInt(1, req.getVigor());
            stats.setInt(2, req.getStrength());
            stats.setInt(3, req.getDexterity());
            stats.setInt(4, req.getIntelligence());
            stats.executeUpdate();

            int playerStatsId;
            try (ResultSet rs = stats.getGeneratedKeys()) {
                if (rs.next()) {
                    playerStatsId = rs.getInt(1);
                } else {
                    throw new SQLException("Failed to retrieve playerStatsId.");
                }
            }

            try (PreparedStatement ps = connection.prepareStatement(weaponEntry)) {
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());

                ps.setString(1, weapon.getName());
                ps.setString(2, weapon.getLore().toString());
                ps.setString(3, weapon.getItemVisual().toString());
                ps.setString(4, weapon.getAbility().toString());
                ps.setTimestamp(6, timestamp);
                ps.setInt(7, playerStatsId);
            }
            connection.commit();
        } catch (SQLException e)
        {
            connection.rollback();
            throw e;
        } finally {
            connection.setAutoCommit(true);
        }
    }

    public void getWeapon(int weaponId) throws SQLException {
        String sql = "SELECT * FROM weapons WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, weaponId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    String lore = rs.getString("lore");
                    String itemVisual = rs.getString("item_visual");
                    String ability = rs.getString("ability");
                    int playerStatsId = rs.getInt("playerStats");
                    Timestamp createdAt = rs.getTimestamp("created_at");

                    System.out.println("Weapon found: " + name);
                } else {
                    System.out.println("No weapon found with ID " + weaponId);
                }
            }
        }
    }

    public void closeConnection() throws SQLException {
        if (connection != null && !connection.isClosed())
        {
            connection.close();
        }
    }

}