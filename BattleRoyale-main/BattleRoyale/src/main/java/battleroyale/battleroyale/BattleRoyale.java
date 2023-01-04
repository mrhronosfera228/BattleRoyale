package battleroyale.battleroyale;

import battleroyale.battleroyale.GameLogic.Game;
import battleroyale.battleroyale.GameLogic.Shop;
import battleroyale.battleroyale.GameLogic.Spectator;
import battleroyale.battleroyale.cars.SpawnArrow;
import battleroyale.battleroyale.commands.GameStartCommand;
import battleroyale.battleroyale.commands.ItemCommand;
import battleroyale.battleroyale.commands.WorkerCommand;
import battleroyale.battleroyale.db.ConnectionPool;
import battleroyale.battleroyale.db.SqlManager;
import battleroyale.battleroyale.events.*;
import battleroyale.battleroyale.items.RoyalItemManager;
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
        Game.copyWorld("oldworld", "newworld", "battle");
        RoyalItemLoad.load(sql);
        RoyalItemManager.initialize();
        TeamLoad.load();
        InventoryLoad.load();
        PlayerTeamLoad.load();
        SquadsLoad.load();
        ChestLoad.load();
        SpawnArrow.load();
        Bukkit.getScheduler().runTaskLater(this, ItemsLoad::load, 100);
        Bukkit.getScheduler().runTaskLater(this, ChestLoadDrop::load, 200);
        new ItemCommand();
        new GameStartCommand();
        new WorkerCommand();
        getServer().getPluginManager().registerEvents(new onDropTake(), this);
        getServer().getPluginManager().registerEvents(new Game(), this);
        getServer().getPluginManager().registerEvents(new onPickUpItem(), this);
        getServer().getPluginManager().registerEvents(new onClickChest(), this);
        getServer().getPluginManager().registerEvents(new onPlayerDamage(), this);
        getServer().getPluginManager().registerEvents(new onRegeneration(), this);
        getServer().getPluginManager().registerEvents(new onPlayerGetDamage(), this);
        getServer().getPluginManager().registerEvents(new onClicked(), this);
        getServer().getPluginManager().registerEvents(new ItemClickChangeTeam(), this);
        getServer().getPluginManager().registerEvents(new Shop(), this);
        getServer().getPluginManager().registerEvents(new Spectator(), this);
        getServer().getPluginManager().registerEvents(new WorkerCommand(), this);
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
