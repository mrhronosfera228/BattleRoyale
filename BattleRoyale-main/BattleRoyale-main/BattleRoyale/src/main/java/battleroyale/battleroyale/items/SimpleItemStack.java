package battleroyale.battleroyale.items;

import battleroyale.battleroyale.utils.UtilColor;
import net.minecraft.server.v1_12_R1.Enchantment;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.List;
import java.util.stream.Collectors;

public class SimpleItemStack extends ItemStack {
    public SimpleItemStack(Material m, int amount, String name, List<String> desc, Object... datas) {
        super(m, amount);
        ItemMeta im = this.getItemMeta();
        im.setDisplayName(UtilColor.toColor("&e%s", new Object[]{name}));
        this.setItemMeta(im);
        if (desc != null && !desc.isEmpty()) {
            im.setLore((List<String>)desc.stream().map(UtilColor::toColor).collect(Collectors.toList()));
            this.setItemMeta(im);
        }

        if (datas != null && datas.length != 0) {
            for(int i = 0; i < datas.length; ++i) {
                Object data = datas[i];
                if (data instanceof Color) {
                    try {
                        LeatherArmorMeta lam = (LeatherArmorMeta)im;
                        lam.setColor((Color)data);
                        this.setItemMeta(lam);
                    } catch (Exception var10) {
                    }
                } else if (data instanceof Enchantment && datas[i + 1] instanceof Integer) {
                    return;
                } else if (data instanceof Integer) {
                    this.setAmount((Integer)data);
                } else if (data instanceof Short) {
                    this.getItemMeta().spigot().setUnbreakable(true);
                    this.setItemMeta(im);
                }
            }

        }
    }
}
