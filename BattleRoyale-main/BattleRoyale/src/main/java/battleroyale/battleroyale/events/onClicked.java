package battleroyale.battleroyale.events;

import battleroyale.battleroyale.BattleRoyale;
import battleroyale.battleroyale.items.RoyalArmor;
import battleroyale.battleroyale.items.RoyalItem;
import battleroyale.battleroyale.items.RoyalItemManager;
import battleroyale.battleroyale.player.RoyalPlayer;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Random;

public class onClicked implements Listener {
    public static final HashMap<String, Long> anticlicker = new HashMap();
    private static final Random r = new Random();

    public onClicked() {
    }
    @EventHandler
    public void onPlayerClicked(PlayerInteractEvent event) {
        if (event.getItem() != null) {
            if ((event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR) && isArmor(event.getItem())) {
                Player p = event.getPlayer();
                Bukkit.getScheduler().scheduleSyncDelayedTask(BattleRoyale.getInstance(), () -> {
                    RoyalPlayer rp = RoyalPlayer.getPlayer(p.getName());
                    int health = 20;
                    int armor = 0;
                    int marmor = 0;
                    int regeneration = 2;
                    ItemStack[] var9 = p.getInventory().getArmorContents();
                    int var10 = var9.length;
                    for (int var11 = 0; var11 < var10; ++var11) {
                        ItemStack is = var9[var11];
                        RoyalItem ri = RoyalItemManager.getItem(is);
                        if (ri != null && ri instanceof RoyalArmor) {
                            RoyalArmor ra = (RoyalArmor) ri;
                            health += ra.getHealth();
                            armor += ra.getArmor();
                            marmor += ra.getMagicArmor();
                            regeneration += ra.getRegeneration();
                        }
                    }
                    rp.setMaxHealth(health);
                    rp.setArmor(armor);
                    rp.setMagicArmor(marmor);
                    rp.setRegeneration(regeneration);
                }, 2L);
            }
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        try {
            InventoryType type = e.getView().getTopInventory().getType();
            ItemStack clicked = e.getCurrentItem();
            if (e.getSlotType() != InventoryType.SlotType.ARMOR && (!e.isShiftClick() || type != InventoryType.CRAFTING || !isArmor(clicked))) {
                if (clicked == null || clicked.getType() == Material.AIR) {
                    int hotbar = e.getHotbarButton();
                    if (hotbar == -1) {
                        return;
                    }
                }
            }
                Bukkit.getScheduler().scheduleSyncDelayedTask(BattleRoyale.getInstance(), () -> {
                    RoyalPlayer rp = RoyalPlayer.getPlayer(p.getName());
                    int health = 20;
                    int armor = 0;
                    int marmor = 0;
                    int regeneration = 2;
                    ItemStack[] var9 = p.getInventory().getArmorContents();
                    int var10 = var9.length;
                    for (int var11 = 0; var11 < var10; ++var11) {
                        ItemStack is = var9[var11];
                        RoyalItem ri = RoyalItemManager.getItem(is);
                        if (ri != null && ri instanceof RoyalArmor) {
                            RoyalArmor ra = (RoyalArmor) ri;
                            health += ra.getHealth();
                            armor += ra.getArmor();
                            marmor += ra.getMagicArmor();
                            regeneration += ra.getRegeneration();
                        }
                    }
                    rp.setMaxHealth(health);
                    rp.setArmor(armor);
                    rp.setMagicArmor(marmor);
                    rp.setRegeneration(regeneration);
                }, 2L);
                anticlicker.put(p.getName(), System.currentTimeMillis());
        } catch (Exception var12) {
            System.out.println("OIC EXCEPTION CAUSER: " + p.getName());
            if (!p.isOp()) {
                ItemStack clicked = e.getCurrentItem();
                if (clicked != null) {
                    p.getInventory().remove(clicked);
                }
            }
        }
    }
    public static boolean isArmor(ItemStack is) {
        return isHelmet(is) || isChestplate(is) || isLeggings(is) || isBoots(is);
    }
    public static boolean isHelmet(ItemStack is) {
        if (is == null) {
            return false;
        } else {
            Material type = is.getType();
            return type == Material.LEATHER_HELMET || type == Material.CHAINMAIL_HELMET || type == Material.GOLD_HELMET || type == Material.IRON_HELMET || type == Material.DIAMOND_HELMET;
        }
    }

    public static boolean isChestplate(ItemStack is) {
        if (is == null) {
            return false;
        } else {
            Material type = is.getType();
            return type == Material.LEATHER_CHESTPLATE || type == Material.CHAINMAIL_CHESTPLATE || type == Material.GOLD_CHESTPLATE || type == Material.IRON_CHESTPLATE || type == Material.DIAMOND_CHESTPLATE;
        }
    }

    public static boolean isLeggings(ItemStack is) {
        if (is == null) {
            return false;
        } else {
            Material type = is.getType();
            return type == Material.LEATHER_LEGGINGS || type == Material.CHAINMAIL_LEGGINGS || type == Material.GOLD_LEGGINGS || type == Material.IRON_LEGGINGS || type == Material.DIAMOND_LEGGINGS;
        }
    }

    public static boolean isBoots(ItemStack is) {
        if (is == null) {
            return false;
        } else {
            Material type = is.getType();
            return type == Material.LEATHER_BOOTS || type == Material.CHAINMAIL_BOOTS || type == Material.GOLD_BOOTS || type == Material.IRON_BOOTS || type == Material.DIAMOND_BOOTS;
        }
    }
}

