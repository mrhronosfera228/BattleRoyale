package battleroyale.battleroyale.items;


import java.util.Random;

public class RoyalWeapon extends RoyalItem {
    Random random = new Random();
    RoyalWeapon(RoyalItemPrototype prototype) {
        super(prototype);
    }

    public int getCriticalChance() {
        return this.getStat(RoyalItemStat.CRITICAL_CHANCE);
    }
    public int getCriticalStrength() {
        return this.getStat(RoyalItemStat.CRITICAL_STRENGTH);
    }
    public int getMinDamage() {
        return this.getStat(RoyalItemStat.MIN_DAMAGE);
    }

    public int getMaxDamage() {
        return this.getStat(RoyalItemStat.MAX_DAMAGE);
    }

    public int getMinMagicDamage() {
        return this.getStat(RoyalItemStat.MIN_MAGIC_DAMAGE);
    }

    public int getMaxMagicDamage() {
        return this.getStat(RoyalItemStat.MAX_MAGIC_DAMAGE);
    }

    public int getPhysicalDamage() {
        return this.getDamage(this.getMinDamage(), this.getMaxDamage());
    }

    public int getMagicalDamage() {
        return this.getDamage(this.getMinMagicDamage(), this.getMaxMagicDamage());
    }

    private int getDamage(int min, int max) {
        return min == max ? min : random.nextInt(max - min) + min;
    }

    public int getPureDamage() {
        return this.getPhysicalDamage() + this.getMagicalDamage();
    }
}
