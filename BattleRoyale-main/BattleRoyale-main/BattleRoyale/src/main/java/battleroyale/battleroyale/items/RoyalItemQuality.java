package battleroyale.battleroyale.items;

public enum RoyalItemQuality {
    COMMON("Обычный предмет", "&f"),
    UNCOMMON("Хороший предмет", "&2"),
    RARE("Редкий предмет", "&9"),
    EPIC("Эпический предмет", "&5"),
    LEGENDARY("Легендарный предмет", "&6"),
    MIFIC("Мифический предмет", "&c"),
    ARTIFACT("Артефакт", "&4");
    private final String name;
    private final String uncoloredPrefix;

    private RoyalItemQuality(String name, String uncoloredPrefix) {
        this.name = name;
        this.uncoloredPrefix = uncoloredPrefix;
    }

    public String getName() {
        return name;
    }

    public String getUncoloredPrefix() {
        return uncoloredPrefix;
    }
}
