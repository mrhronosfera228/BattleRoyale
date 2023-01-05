package battleroyale.battleroyale;

import battleroyale.battleroyale.GameLogic.Game;

import battleroyale.battleroyale.GameLogic.Shop;
import battleroyale.battleroyale.GameLogic.Spectator;
import battleroyale.battleroyale.commands.WorkerCommand;
import battleroyale.battleroyale.events.*;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;

//Загрузка всех событий в главный класс
public class EventsLoader {
    private static PluginManager pm;
    public static void load() {
        pm = Bukkit.getServer().getPluginManager();
        register(new Game());
        register(new onDropTake());
        register(new Game());
        register(new onPickUpItem());
        register(new onClickChest());
        register(new onPlayerDamage());
        register(new onRegeneration());
        register(new onPlayerGetDamage());
        register(new onClicked());
        register(new ItemClickChangeTeam());
        register(new Shop());
        register(new Spectator());
        register(new WorkerCommand());
    }
    private static void register(Listener l) {
        pm.registerEvents(l, BattleRoyale.getInstance());
    }
}
