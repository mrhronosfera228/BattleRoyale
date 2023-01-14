package battleroyale.battleroyale;

import battleroyale.battleroyale.GameLogic.Game;
import battleroyale.battleroyale.loaders.PlaneLoad;
import battleroyale.battleroyale.commands.GameStartCommand;
import battleroyale.battleroyale.commands.ItemCommand;
import battleroyale.battleroyale.commands.WorkerCommand;
import battleroyale.battleroyale.db.SqlManager;
import battleroyale.battleroyale.items.RoyalItemManager;
import battleroyale.battleroyale.loaders.*;
import battleroyale.battleroyale.utils.UtilChat;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class BattleRoyale extends JavaPlugin {
    private static BattleRoyale instance;
    private SqlManager sql;
    @Override
    public void onEnable() {
        Bukkit.getServer().setWhitelist(true);
        instance = this;
        sql = new SqlManager(this);
        Game.copyWorld("oldworld",  "battle");
        RoyalItemLoad.load(sql);
        RoyalItemManager.initialize();
        TeamLoad.load();
        InventoryLoad.load();
        PlayerTeamLoad.load();
        ChestLoad.load();
        PlaneLoad.load();
        CarLoad.load();
        Bukkit.getScheduler().runTaskLater(this, ItemsLoad::load, 100);
        Bukkit.getScheduler().runTaskLater(this, ChestLoadDrop::load, 200);
        new ItemCommand();
        new GameStartCommand();
        new WorkerCommand();
        EventsLoader.load();
        Bukkit.getServer().setWhitelist(false);
    }


    @Override
    public void onDisable() {
        sql.close();
    }

    public static SqlManager getSql() {
        return instance.sql;
    }

    public static BattleRoyale getInstance() {
        return instance;
    }
    public static void sendMessage(Player p, String msg, Object... args) {
        UtilChat.ps("&a&lBattleRoyal", p, msg, args);
    }
    public static void sendMessage(CommandSender p, String msg, Object... args) {
        UtilChat.ps("&a&lBattleRoyal", p, msg, args);
    }

}
