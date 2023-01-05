package battleroyale.battleroyale.loaders;
import battleroyale.battleroyale.BattleRoyale;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.metadata.FixedMetadataValue;

import java.util.List;
import java.util.Random;

public class ChestLoadDrop {
    private static final Random random = new Random();
    public static void load() {
        toLoad(ChestLoad.common, "common");
        toLoad(ChestLoad.rare, "rare");
        toLoad(ChestLoad.epic, "epic");
        toLoad(ChestLoad.legendary, "legendary");
    }
    public static void toLoad(List<Location> map, String name) {
        for (int i = 0; i < map.size(); i++) {
            Location location = map.get(i);
            if (!location.getBlock().getType().equals(Material.CHEST)) {
                location.getBlock().setType(Material.CHEST);
            }
            if (location.getBlock().getType().equals(Material.CHEST)) {
                location.getBlock().setMetadata(name, new FixedMetadataValue(BattleRoyale.getInstance(), "chest"));
                Chest chest = (Chest) location.getBlock().getState();
                switch (name) {
                    case "common":
                        if (ItemsLoad.qCommon.size() != 0) {
                            chest.getBlockInventory().addItem(ItemsLoad.qCommon.get(random.nextInt(ItemsLoad.qCommon.size())));
                            System.out.println(ItemsLoad.qCommon.size());
                        }
                        break;
                    case "rare":
                        if (ItemsLoad.qRare.size() != 0) {
                            chest.getBlockInventory().addItem(ItemsLoad.qRare.get(random.nextInt(ItemsLoad.qRare.size())));
                            System.out.println(ItemsLoad.qRare.size());
                        }
                        break;
                    case "epic":
                        if (ItemsLoad.qEpic.size() != 0) {
                            chest.getBlockInventory().addItem(ItemsLoad.qEpic.get(random.nextInt(ItemsLoad.qEpic.size())));
                            System.out.println(ItemsLoad.qEpic.size());
                        }
                        break;
                    case "legendary":
                        if (ItemsLoad.qLegendary.size() != 0) {
                            chest.getBlockInventory().addItem(ItemsLoad.qLegendary.get(random.nextInt(ItemsLoad.qLegendary.size())));
                            System.out.println(ItemsLoad.qLegendary.size());
                        }
                        break;
                }
            }
        }
    }
}
