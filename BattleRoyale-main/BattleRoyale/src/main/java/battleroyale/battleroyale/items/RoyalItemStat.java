package battleroyale.battleroyale.items;

public enum RoyalItemStat {
    ORIGINAL_ID("original_id"),
    ORIGINAL_DATA("original_data"),
    MIN_DAMAGE("min_damage"),
    MAX_DAMAGE("max_damage"),
    CRITICAL_CHANCE("critical_chance"),
    CRITICAL_STRENGTH("critical_strength"),
    ARMOR("armor"),
    RESISTANCE("resistance"),
    HEALTH("health"),
    REGENERATION("regeneration"),
    MIN_MAGIC_DAMAGE("min_mdamage"),
    MAX_MAGIC_DAMAGE("max_mdamage"),
    MAGIC_ARMOR("marmor");
    private final String column;
    private RoyalItemStat(String column) {
        this.column = column;
    }
    public String getColumn() {
        return column;
    }
}
