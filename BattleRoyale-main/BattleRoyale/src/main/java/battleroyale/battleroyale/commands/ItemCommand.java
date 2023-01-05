package battleroyale.battleroyale.commands;

import battleroyale.battleroyale.BattleRoyale;
import battleroyale.battleroyale.items.RoyalItemManager;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

//Команды для получения кастомных предметов
public class ItemCommand extends AbstractCommand {
    public ItemCommand() {
        super("item");
    }

    @Override
    public void execute(CommandSender commandSender, String label, String[] args) {
        Player p = (Player) commandSender;
        if (args.length == 0) {
            commandSender.sendMessage("/item create {id}, {original_id}");
            commandSender.sendMessage("/item stats add {id} {stat_name}");
            commandSender.sendMessage("/item get {id}");
            commandSender.sendMessage("/item list");
            return;
        }
        if (args[0].equalsIgnoreCase("get")) {
            if (args.length == 3) {
                int amount = 1;
                int id = 0;
                try {
                    id = Integer.parseInt(args[1]);
                } catch (NumberFormatException var74) {
                }

                try {
                    amount = Integer.parseInt(args[2]);
                } catch (NumberFormatException var73) {
                }

                if (amount < 1) {
                    amount = 1;
                } else if (amount > 64) {
                    amount = 64;
                }

                if (id > 0 &&  RoyalItemManager.getItem(id) != null) {
                    RoyalItemManager.getItem(id).giveToPlayer(p, amount);
                    BattleRoyale.sendMessage(p, "&aПредмет был успешно добавлен к вам в инвентарь!");
                } else {
                    BattleRoyale.sendMessage(p, "&cВы ввели неверный номер предмета или предмета с указанным номером не существует!");
                }
            }
        }
    }
}
