package battleroyale.battleroyale.db;

import battleroyale.battleroyale.BattleRoyale;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionPool {
    private HikariDataSource dataSource;
    private BattleRoyale plugin;
    private String host;
    private String user;
    private String password;
    private String port;
    private String database;
    private int maximumConnections;
    private int minimumConnections;
    private void init() {
        host = plugin.getConfig().getString("sql.hostname");
        port = plugin.getConfig().getString("sql.port");
        database = plugin.getConfig().getString("sql.database");
        user = plugin.getConfig().getString("sql.username");
        password = plugin.getConfig().getString("sql.password");
        minimumConnections = plugin.getConfig().getInt("sql.PoolMinimumConnections");
        maximumConnections = plugin.getConfig().getInt("sql.PoolMaximumConnections");
    }
    public ConnectionPool(BattleRoyale plugin) {
        this.plugin = plugin;
        setupPool();
    }
    private void setupPool() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(
                "jdbc:mysql://" +
                        "127.0.0.1" +
                        ":" +
                        "3306" +
                        "/" +
                        "battleroyal"
        );
        config.setDriverClassName("com.mysql.jdbc.Driver");
        config.setUsername("root");
        config.setPassword("");
        dataSource = new HikariDataSource(config);
    }
    public void close(Connection conn, PreparedStatement ps, ResultSet res) {
        if (conn != null) try {
            conn.close();
        } catch (SQLException ignored) {
        }
        if (ps != null) try {
            ps.close();
        } catch (SQLException ignored) {
        }
        if (res != null) try {
            res.close();
        } catch (SQLException ignored) {
        }
    }

    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
    public void closePool() {
        if (database != null && !dataSource.isClosed()) {
            dataSource.close();
        }
    }
}
