package battleroyale.battleroyale.loaders;
import battleroyale.battleroyale.BattleRoyale;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.metadata.FixedMetadataValue;

import java.util.List;
import java.util.Random;

public class ChestLoadDrop {
    private static Random random = new Random();
    public static void load() {
        toLoad(ChestLoad.getCommon(), "common");
        toLoad(ChestLoad.getRare(), "rare");
        toLoad(ChestLoad.getEpic(), "epic");
        toLoad(ChestLoad.getLegendary(), "legendary");
    }
    private static void toLoad(List<Location> map, String name) {
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
                        if (ItemsLoad.getqCommon().size() != 0) {
                            chest.getBlockInventory().addItem(ItemsLoad.getqCommon().get(random.nextInt(ItemsLoad.getqCommon().size())));
                            System.out.println(ItemsLoad.getqCommon().size());
                        }
                        break;
                    case "rare":
                        if (ItemsLoad.getqRare().size() != 0) {
                            chest.getBlockInventory().addItem(ItemsLoad.getqRare().get(random.nextInt(ItemsLoad.getqRare().size())));
                            System.out.println(ItemsLoad.getqRare().size());
                        }
                        break;
                    case "epic":
                        if (ItemsLoad.getqEpic().size() != 0) {
                            chest.getBlockInventory().addItem(ItemsLoad.getqEpic().get(random.nextInt(ItemsLoad.getqEpic().size())));
                            System.out.println(ItemsLoad.getqEpic().size());
                        }
                        break;
                    case "legendary":
                        if (ItemsLoad.getqLegendary().size() != 0) {
                            chest.getBlockInventory().addItem(ItemsLoad.getqLegendary().get(random.nextInt(ItemsLoad.getqLegendary().size())));
                            System.out.println(ItemsLoad.getqLegendary().size());
                        }
                        break;
                }
            }
        }
    }
}
