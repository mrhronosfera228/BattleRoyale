package battleroyale.battleroyale.player;

import battleroyale.battleroyale.BattleRoyale;
import org.bukkit.EntityEffect;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;

public abstract class RoyalDamageable {
    protected int health;
    protected int max_health;

    protected Player player;
    protected RoyalDamageable(Player player) {
        this.player = player;
        //получаем значение здоровья игрока (если игрок имеет метаданные то получаем значение из них если нет то устанавливаем 20
        this.health = this.max_health = player.hasMetadata("royal_health") ? player.getMetadata("royal_health").get(0).asInt() : 20;
    }
    public void damage(RoyalDamageable damager, int damage) {
        Player player1 = getPlayer();
        if (player1 != null) {
            player1.playEffect(EntityEffect.HURT);
            int health = this.getHealth() - damage;
            if (health <= 0) {
                this.kill(damager);
            } else {
                this.setHealth(health);
            }
        }
    }
    public abstract void kill(RoyalDamageable var1);

    public void setHealth(int health) {
        Player player1 = this.getPlayer();
        this.health = health = Math.min(health, this.getMax_health());
        if (health <= 0) {
            this.kill(null);
        } else if (player1 != null && player1.hasMetadata("royal_health")) {
            player1.setMetadata("royal_health", new FixedMetadataValue(BattleRoyale.getInstance(), health));

        }
    }

    public int getMax_health() {
        return max_health;
    }

    public int getHealth() {
        return health;
    }

    public Player getPlayer() {
        return player;
    }
    public abstract int getArmor();

    public abstract int getMagicArmor();
}
