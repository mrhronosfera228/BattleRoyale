package battleroyale.battleroyale.items;

public class RoyalArmor extends RoyalItem{
    RoyalArmor(RoyalItemPrototype prototype) {
        super(prototype);
    }

    public int getArmor() {
        return this.getStat(RoyalItemStat.ARMOR);
    }

    public int getMagicArmor() {
        return this.getStat(RoyalItemStat.MAGIC_ARMOR);
    }


    public int getHealth() {
        return this.getStat(RoyalItemStat.HEALTH);
    }

    public int getRegeneration() {
        return this.getStat(RoyalItemStat.REGENERATION);
    }
}
