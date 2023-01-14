package battleroyale.battleroyale;

import battleroyale.battleroyale.GameLogic.Game;

import battleroyale.battleroyale.GameLogic.Shop;
import battleroyale.battleroyale.GameLogic.Spectator;
import battleroyale.battleroyale.commands.WorkerCommand;
import battleroyale.battleroyale.events.*;
import battleroyale.battleroyale.loaders.CarLoad;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;

//Загрузка всех событий в главный класс
public class EventsLoader {
    private static PluginManager pm;
    public static void load() {
        pm = Bukkit.getServer().getPluginManager();
        register(new Shop());
        register(new Spectator());
        register(new WorkerCommand());
        register(new ItemClickChangeTeam());
        register(new onClickChest());
        register(new onClicked());
        register(new onDeathPlayer());
        register(new onDropInLobby());
        register(new onDropTake());
        register(new onHunger());
        register(new onPickUpItem());
        register(new onPlayerDamage());
        register(new onPlayerGetDamage());
        register(new onPlayerJoin());
        register(new onPlayerLeave());
        register(new onPlayerMove());
        register(new onRegeneration());
    }
    private static void register(Listener l) {
        pm.registerEvents(l, BattleRoyale.getInstance());
    }
}
