package battleroyale.battleroyale.commands;

import battleroyale.battleroyale.BattleRoyale;
import battleroyale.battleroyale.db.Callback;
import battleroyale.battleroyale.db.SqlManager;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.metadata.FixedMetadataValue;

import java.sql.ResultSet;
import java.sql.SQLException;

//Команда для записи координат сундука в базу данных
public class WorkerCommand extends AbstractCommand implements Listener {

    public WorkerCommand() {
        super("worker");
    }

    @Override
    public void execute(CommandSender commandSender, String label, String[] args) {
        if (args.length == 1) {
            if (commandSender instanceof Player) {
                Player player = (Player) commandSender;
                switch (args[0]) {
                    case "common":
                        HasMeta(player, "common");
                        BattleRoyale.sendMessage(player, "&aВы начали работу над сундуками common, чтобы закончить напишите &c/worker stop");
                        break;
                    case "rare":
                        HasMeta(player, "rare");
                        BattleRoyale.sendMessage(player, "&aВы начали работу над сундуками rare, чтобы закончить напишите &c/worker stop");
                        break;
                    case "epic":
                        HasMeta(player, "epic");
                        BattleRoyale.sendMessage(player, "&aВы начали работу над сундуками epic, чтобы закончить напишите &c/worker stop");
                        break;
                    case "legendary":
                        HasMeta(player, "legendary");
                        BattleRoyale.sendMessage(player, "&aВы начали работу над сундуками legendary, чтобы закончить напишите &c/worker stop");
                        break;
                    case "stop":
                        HasMeta(player, "stop");
                        break;
                    default:
                        BattleRoyale.sendMessage(commandSender, "&cincorrect command");
                }
            }
        }
    }
    public void HasMeta(Player p, String s) {
        if (p.hasMetadata("common")) {
            p.removeMetadata("common", BattleRoyale.getInstance());
            BattleRoyale.sendMessage(p, "&cВы закончили работу над сундуками common");
        }
        if (p.hasMetadata("rare")) {
            p.removeMetadata("rare", BattleRoyale.getInstance());
            BattleRoyale.sendMessage(p, "&cВы закончили работу над сундуками rare");
        }
        if (p.hasMetadata("epic")) {
            p.removeMetadata("epic", BattleRoyale.getInstance());
            BattleRoyale.sendMessage(p, "&cВы закончили работу над сундуками epic");
        }
        if (p.hasMetadata("legendary")) {
            p.removeMetadata("legendary", BattleRoyale.getInstance());
            BattleRoyale.sendMessage(p, "&cВы закончили работу над сундуками legendary");
        }
        if (!s.equalsIgnoreCase("stop")) {
            p.setMetadata(s, new FixedMetadataValue(BattleRoyale.getInstance(), "chest"));
        }
    }
    @EventHandler
    public void onPlacedChest(BlockPlaceEvent event) {
        if (event.getBlock().getType().equals(Material.CHEST)) {
            placed(event, "common", "chest_common");
            placed(event, "rare", "chest_rare");
            placed(event, "epic", "chest_epic");
            placed(event, "legendary", "chest_legendary");
        }
    }
    public void placed(BlockPlaceEvent event, String c, String tableName) {
        if (event.getPlayer().hasMetadata(c)) {
            if (event.getBlock().getType().equals(Material.CHEST)) {
                Block block = event.getBlock();
                block.setMetadata(c, new FixedMetadataValue(BattleRoyale.getInstance(), "block"));
                SqlManager.findAsync("INSERT INTO `%s` (`world`, `x`, `y`, `z`) VALUES ('%s','%s','%s','%s');", new Callback() {
                    @Override
                    public void onQueryDone(ResultSet result) {
                        try {
                            result.close();
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }, tableName, block.getWorld().getName(), block.getX(), block.getY(), block.getZ());
            }
        }
    }
}
