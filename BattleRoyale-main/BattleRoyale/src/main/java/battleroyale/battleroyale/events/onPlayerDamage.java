package battleroyale.battleroyale.events;

import battleroyale.battleroyale.GameLogic.Game;
import battleroyale.battleroyale.items.RoyalItem;
import battleroyale.battleroyale.items.RoyalItemManager;
import battleroyale.battleroyale.items.RoyalWeapon;
import battleroyale.battleroyale.player.RoyalDamageable;
import battleroyale.battleroyale.player.RoyalPlayer;
import battleroyale.battleroyale.player.Squad;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.Random;

public class onPlayerDamage implements Listener {
    private static Random random = new Random();
    Scoreboard board = Bukkit.getScoreboardManager().getMainScoreboard();
    protected static final long player_attack_speed = 260L;
    public static final HashMap<String, Long> attackTime = new HashMap<>();
    public onPlayerDamage (){
    }
    @EventHandler
    public void onEntityDamageEntity(EntityDamageByEntityEvent event) {
        event.setCancelled(true);
        if (Game.GameStart) {
            try {
                Entity absoluteDamager = event.getDamager();
                Player damager = null;
                Player target = null;
                RoyalItem item = null;
                RoyalPlayer tar;
                RoyalPlayer dam = null;
                if (event.getEntity() != null) {
                    if (absoluteDamager instanceof  Player) {
                        damager = (Player) event.getDamager();
                    } else if (absoluteDamager instanceof Projectile) {
                        Projectile pj = (Projectile) absoluteDamager;
                        if (pj instanceof Arrow && pj.getShooter() instanceof Player) {
                            damager = (Player) pj.getShooter();
                            pj.remove();
                        }
                    }
                    if (!(event.getEntity() instanceof Player) && !event.getEntity().hasMetadata("royal_mob") && !(event.getEntity() instanceof ArmorStand)) {
                        event.getEntity().remove();
                    }

                    if (event.getEntity() instanceof Player) {
                        target = (Player) event.getEntity();
                    }

                    if (target == null && event.getDamage() == 0.0) {
                        event.setCancelled(false);
                        return;
                    }

                    if (damager != null && damager == target) {
                        return;
                    }
                    if (damager != null) {
                        dam = RoyalPlayer.getPlayer(damager.getName());
                    }

                    if (item == null && damager != null) {
                        ItemStack hand = damager.getInventory().getItemInMainHand();

                        item = RoyalItemManager.getItem(hand);

                        if (hand == null || hand.getType() != Material.BOW) {
                            item = RoyalItemManager.getItem(hand);
                        }
                    }

                    if (damager != null && target != null) {
                        RoyalDamageable damagere = RoyalPlayer.getPlayer(damager.getName());
                        tar = RoyalPlayer.getPlayer(target.getName());
                        if (!checkCanAttack(damager, false)) {
                            return;
                        }
                        if (board.getEntryTeam(damager.getName()) != null && board.getEntryTeam(target.getName()) != null) {
                            if (board.getEntryTeam(damager.getName()).getName().equalsIgnoreCase(board.getEntryTeam(target.getName()).getName())) {
                                return;
                            }
                        }
                        damager.sendMessage("" + tar.getArmor());
                        damager.sendMessage("" + tar.getHealth());
                        Vector vector = new Vector(damager.getLocation().getDirection().getX() / 2,  0.35, damager.getLocation().getDirection().getZ() / 2);
                        target.setVelocity(vector);
                        tar.damage(damagere, getFinalDamage(dam, item, tar));
                    }
                }
            } catch (IllegalStateException var18) {
                var18.printStackTrace();
            }
        }
    }
    public static boolean checkCanAttack(Player p, boolean bow) {
        String name = p.getName();
        Long current = System.currentTimeMillis();
        Long value = attackTime.get(name);
        if (value == null) {
            value = 0L;
        }

        long l = bow ? 500L : 600L;
        if (current - value < l) {
            return false;
        } else {
            attackTime.put(name, current);
            return true;
        }
    }

    public static int getFinalDamage(RoyalPlayer damager, RoyalItem item, RoyalDamageable victim) {
        if (item instanceof RoyalWeapon) {
            RoyalWeapon weapon = (RoyalWeapon) item;
            return getFinalDamage(damager, weapon.getPhysicalDamage(), weapon.getMagicalDamage(), weapon.getCriticalChance(), weapon.getCriticalStrength(), victim);
        } else {
            return getFinalDamage(damager, random.nextInt(2) + 1, 0, 0, 0, victim);
        }
    }

    public static int getFinalDamage(RoyalPlayer damager, int physical, int magical, int criticalChance, int criticalSrength, RoyalDamageable victim) {

        float pure = (float)(physical + magical);
        int armorReductor = 100;
        physical = (int)((float)physical * (1.0F - 0.75F * Math.min(1.0F, (float)victim.getArmor() / (float)armorReductor)));
        magical = (int)((float)magical * (1.0F - 0.75F * Math.min(1.0F, (float)victim.getMagicArmor() / (float)armorReductor)));
        float total = (float)(physical + magical);
        if (random.nextInt(100) < criticalChance) {
            total += (int)(total * ((float)criticalSrength / 100));
        }

        return (int)(total < 1.0F ? 1.0F : total);
    }
}
