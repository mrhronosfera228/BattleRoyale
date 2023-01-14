package battleroyale.battleroyale.db;

import battleroyale.battleroyale.BattleRoyale;
import org.bukkit.Bukkit;

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
    public static void findAsync(PreparedStatement ps, String query, final Callback callback) {
        Bukkit.getScheduler().runTaskAsynchronously(sqlPlugin.getPlugin(), new Runnable() {
            @Override
            public void run() {
                ResultSet result;
                try {
                    if (getStatement(query).equals(SqlManager.Statements.SELECT)) {
                        result = ps.executeQuery();
                        Bukkit.getScheduler().runTask(sqlPlugin.getPlugin(), () -> callback.onQueryDone(new MyResultSet(result)));
                    } else if (getStatement(query).equals(Statements.INSERT)) {
                        ps.executeUpdate();
                        result = ps.getGeneratedKeys();
                        Bukkit.getScheduler().runTask(sqlPlugin.getPlugin(), () -> callback.onQueryDone(new MyResultSet(result)));
                    } else {
                        ps.executeUpdate();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public static PreparedStatement prepare(String query, Object... args) {
        PreparedStatement ps = null;
        try {
            Connection connection = BattleRoyale.getSql().getPool().getConnection();
            if (getStatement(query).equals(Statements.INSERT)) {
                ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            } else {
                ps = connection.prepareStatement(query);
            }
            for (int i = 1; i < args.length + 1; i++) {
                ps.setObject(i, args[i - 1]);
            }
            return ps;
        } catch (SQLException var5) {
            var5.printStackTrace();
            return ps;
        }
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
