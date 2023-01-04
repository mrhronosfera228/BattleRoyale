package battleroyale.battleroyale.items;

import battleroyale.battleroyale.utils.UtilChat;
import battleroyale.battleroyale.utils.UtilColor;
import com.google.common.collect.Lists;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class RoyalItemPrototype {
    private final int id;
    private final int[] stats;
    private String name;
    private List<String> description;
    private RoyalItemQuality quality;
    public RoyalItemPrototype(int id, ResultSet set) throws SQLException {
        this.id = id;
        this.stats = new int [RoyalItemStat.values().length];
        RoyalItemStat[] var3 = RoyalItemStat.values();
        int var4 = var3.length;

        for (int var5 = 0; var5 < var4; ++var5) {
            RoyalItemStat stat = var3[var5];
            this.stats[stat.ordinal()] = set.getInt(stat.getColumn());
        }

        this.quality = RoyalItemQuality.valueOf(set.getString("quality"));
        this.name = UtilColor.toColor(set.getString("name"));
        this.description = Arrays.asList(set.getString("description").split("\\r?\\n?\\|"));
    }

    RoyalItemPrototype(RoyalItemPrototype toCopy) {
        if (toCopy == null) {
            this.id = 0;
            this.stats = new int[RoyalItemStat.values().length];
            this.quality = RoyalItemQuality.COMMON;
            this.name = "???";
            this.description = Lists.newArrayList();
        } else {
            this.id = toCopy.id;
            this.stats = toCopy.stats;
            this.quality = toCopy.quality;
            this.name = toCopy.name;
            this.description = toCopy.description;
        }
    }
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setQuality(RoyalItemQuality quality) {
        this.quality = quality;
    }

    public void setDescription(List<String> description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getDescription() {
        return description;
    }

    public RoyalItemQuality getQuality() {
        return quality;
    }

    int[] getStats() {
        return this.stats;
    }
    int getStat(RoyalItemStat stat) {
        return this.stats[stat.ordinal()];
    }
}
