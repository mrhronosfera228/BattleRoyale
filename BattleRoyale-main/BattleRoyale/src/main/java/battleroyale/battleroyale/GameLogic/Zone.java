package battleroyale.battleroyale.GameLogic;

import battleroyale.battleroyale.BattleRoyale;
import battleroyale.battleroyale.player.RoyalPlayer;
import battleroyale.battleroyale.player.RoyaleAlivePlayers;
import org.bukkit.*;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class Zone {
    private World w;
    public WorldBorder worldBorder;
    private BattleRoyale plugin;
    private int endZoneSize;
    private boolean flag;
    private int timer;

    public Zone(BattleRoyale p) {
        plugin = p;
    }

    private float zoneSize;
    private int frequency;
    private int zoneTimer; //Основной таймер зоны
    private int zoneShrinkTimer;
    private float nextZoneSizeMultiplier;
    private int waitMultiplier;
    private float zoneDamageMultiplier;
    private int airDropTimer;
    private int airDropMin;
    private int airDropMax;
    public static HashMap<Integer, Player> players = new HashMap();
    //Инициализация стартовой зоны для соло игры
    public void StartZone(World world) {
        flag = true;
        frequency = 20;
        w = world;
        timer = 180 * 20;
        zoneSize = 1500;
        zoneTimer = 180 * 20;
        zoneShrinkTimer = 120 * 20;
        worldBorder = w.getWorldBorder();
        worldBorder.setSize(zoneSize);
        worldBorder.setCenter(0, 0);
        nextZoneSizeMultiplier = 0.5F;
        waitMultiplier = 30 * 20;
        zoneDamageMultiplier = 1.4F;
        airDropTimer = 250 * 20;
        airDropMin = 100 * 20;
        airDropMax = 200 * 20;
        endZoneSize = 50;
        Bukkit.broadcastMessage("=-=-=-=-=-=-=-=-=-=");
        Bukkit.broadcastMessage("Размер зоны установлен: " + zoneSize);
        Bukkit.broadcastMessage("=-=-=-=-=-=-=-=-=-=");
        Bukkit.broadcastMessage("§cЦентр зоны : \n x = 0\n z = 0");
    }
    //Инициализация стартовой зоны для игры в командах
    public void StartTeamsZone(World world) {
        flag = true;
        frequency = 20;
        w = world;
        timer = 360 * 20;
        zoneSize = 4500;
        zoneTimer = 360 * 20;
        zoneShrinkTimer = 240 * 20;
        worldBorder = w.getWorldBorder();
        worldBorder.setSize(zoneSize);
        worldBorder.setCenter(0, 0);
        nextZoneSizeMultiplier = 0.5F;
        waitMultiplier = 60 * 20;
        zoneDamageMultiplier = 1.4F;
        airDropTimer = 250 * 20;
        airDropMin = 100 * 20;
        airDropMax = 200 * 20;
        endZoneSize = 100;
        Bukkit.broadcastMessage("=-=-=-=-=-=-=-=-=-=");
        Bukkit.broadcastMessage("Размер зоны установлен: " + zoneSize);
        Bukkit.broadcastMessage("=-=-=-=-=-=-=-=-=-=");
        Bukkit.broadcastMessage("§cЦентр зоны : \n x = 0\n z = 0");
    }


    public void SecProcessor() {
        if (flag) {
            //условие окончания сужения зоны
            if (zoneSize < endZoneSize) {
                Bukkit.broadcastMessage("Зона достигла минимального размера! Теперь только хардкорное пвп ААА!!");
                flag = false;
            }

            //Время до сужения зоны
            if (zoneTimer >= 0) {
                if (zoneTimer == 0) {
                    Bukkit.broadcastMessage("Зона начала сужаться! И продолжит еще - " + zoneShrinkTimer / 20 + " секунд!");
                    zoneTimer = -1;
                    Bukkit.broadcastMessage("=-=-=-=-=-=-=-=-=-=");
                    Bukkit.broadcastMessage("Зона начала сужаться");
                    Bukkit.broadcastMessage("=-=-=-=-=-=-=-=-=-=");
                    zoneSize = zoneSize * nextZoneSizeMultiplier;
                    worldBorder.setSize(zoneSize, zoneShrinkTimer/20);
                    zoneShrinkTimer -= frequency;
                }
                else {
                    if((zoneTimer / 20) % 5 == 0) {
                        Bukkit.broadcastMessage("До начала сужения зоны осталось - " + zoneTimer / 20 + " секунд!!!!");
                    }
                    zoneTimer -= frequency;
                }
            }

            //Сужение зоны
            else if (zoneShrinkTimer >= 0) {

                if (zoneShrinkTimer == 0) {
                    timer = timer - waitMultiplier;
                    zoneTimer = timer;
                    zoneShrinkTimer = timer / 3;
                    /*Пока думаю над реализацией
                    Player player = RoyaleAlivePlayers.GetRandomPlayer();
                    Location location = player.getLocation();
                    worldBorder.setCenter(location.getBlockX(), location.getBlockZ());*/
                    Bukkit.broadcastMessage("=-=-=-=-=-=-=-=-=-=");
                    Bukkit.broadcastMessage("Размер зоны установлен: " + zoneSize);
                    Bukkit.broadcastMessage("=-=-=-=-=-=-=-=-=-=");
                    //Bukkit.broadcastMessage("§cЦентр зоны : \n x = " + location.getBlockX() + "\n z = " + location.getBlockZ());
                    Bukkit.broadcastMessage("До начала сужения следующей зоны осталось - " + zoneTimer / 20 + " секунд");
                    zoneTimer -= frequency;
                    Bukkit.broadcastMessage("=-=-=-=-=-=-=-=-=-=");
                }
                else {
                    if((zoneShrinkTimer / 20) % 5 == 0) {
                        Bukkit.broadcastMessage("До конца сужения зоны осталось - " + zoneShrinkTimer / 20 + " секунд!!!!");
                    }
                    zoneShrinkTimer -= frequency;
                }
            }
            //Кастомный Урон зоны
            for(Player player : Bukkit.getServer().getOnlinePlayers()){
                if (player.getGameMode() == GameMode.ADVENTURE && RoyalPlayer.getPlayer(player.getName()).getHealth() > 0) {
                    if(!worldBorder.isInside(player.getLocation())){
                        RoyalPlayer.getPlayer(player.getName()).damage(null,  RoyalPlayer.getPlayer(player.getName()).getMaxHealth()/(int)((20 * 1.4 / zoneDamageMultiplier)));
                    }
                }
            }
        }
    }
}

