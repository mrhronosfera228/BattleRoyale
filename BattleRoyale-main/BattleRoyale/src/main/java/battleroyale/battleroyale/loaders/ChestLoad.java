package battleroyale.battleroyale.loaders;

import battleroyale.battleroyale.db.Callback;
import battleroyale.battleroyale.db.SqlManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChestLoad {
    public static List<Location> common = new ArrayList<>();
    public static List<Location> rare = new ArrayList<>();
    public static List<Location> epic = new ArrayList<>();
    public static List<Location> legendary = new ArrayList<>();
    public static void load() {
        toLoad("chest_common", common);
        toLoad("chest_rare", rare);
        toLoad("chest_epic", epic);
        toLoad("chest_legendary", legendary);
    }
    public static void toLoad(String tableName, List<Location> map) {
        SqlManager.findAsync("SELECT * FROM " + tableName, new Callback() {
            @Override
            public void onQueryDone(ResultSet result) {
                try {
                    while (result.next()) {
                       map.add(new Location(Bukkit.getWorld(result.getString("world")), result.getInt("x"), result.getInt("y"), result.getInt("z")));

                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } finally {
                    try {
                        result.close();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
    }
}
