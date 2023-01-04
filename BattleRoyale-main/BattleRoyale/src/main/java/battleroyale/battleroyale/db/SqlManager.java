package battleroyale.battleroyale.db;

import battleroyale.battleroyale.BattleRoyale;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import java.sql.*;

public class SqlManager {
     public static SqlManager sqlPlugin;
     private final ConnectionPool pool;
     private final BattleRoyale plugin;
     public SqlManager(BattleRoyale plugin) {
         this.pool = new ConnectionPool(plugin);
         this.plugin = plugin;
         sqlPlugin = this;
     }
    public void createTable(String query) {
        try (Connection con = pool.getConnection(); PreparedStatement ps = con.prepareStatement(query)){
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void findAsync(final String query, final Callback callback, Object... args) {
        Bukkit.getScheduler().runTaskAsynchronously(sqlPlugin.getPlugin(), new Runnable() {
            @Override
            public void run() {
                ResultSet result;
                try {
                    Connection con = BattleRoyale.getSql().getPool().getConnection();
                    PreparedStatement ps = con.prepareStatement(String.format(query, args));
                    if (getStatement(String.format(query, args)).equals(SqlManager.Statements.SELECT)) {
                        result = ps.executeQuery();
                        Bukkit.getScheduler().runTask(sqlPlugin.getPlugin(), () -> callback.onQueryDone(new MyResultSet(result)));
                    } else if (getStatement(String.format(query, args)).equals(Statements.INSERT)) {
                        ps.executeUpdate(String.format(query, args), Statement.RETURN_GENERATED_KEYS);
                        result = ps.getGeneratedKeys();
                        Bukkit.getScheduler().runTask(sqlPlugin.getPlugin(), () -> callback.onQueryDone(new MyResultSet(result)));
                    } else {
                        ps.executeUpdate(String.format(query, args));
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
    protected static Statements getStatement(String query) {
        String trimmed = query.trim();
        Statements[] var3 = SqlManager.Statements.values();
        int var4 = var3.length;

        for(int var5 = 0; var5 < var4; ++var5) {
            Statements sts = var3[var5];
            if (trimmed.startsWith(sts.name())) {
                return sts;
            }
        }

        return SqlManager.Statements.SELECT;
    }

    protected static enum Statements {
        SELECT,
        INSERT,
        UPDATE,
        DELETE,
        DO,
        REPLACE,
        LOAD,
        HANDLER,
        CALL,
        CREATE,
        ALTER,
        DROP,
        TRUNCATE,
        RENAME;

        private Statements() {
        }
    }
        public void close() {
         pool.closePool();
    }

    public BattleRoyale getPlugin() {
        return plugin;
    }

    public  ConnectionPool getPool() {
        return pool;
    }
}
