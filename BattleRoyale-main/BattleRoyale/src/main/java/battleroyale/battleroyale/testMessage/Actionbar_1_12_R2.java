package battleroyale.battleroyale.testMessage;

import net.minecraft.server.v1_12_R1.ChatMessageType;
import net.minecraft.server.v1_12_R1.IChatBaseComponent;
import net.minecraft.server.v1_12_R1.PacketPlayOutChat;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class Actionbar_1_12_R2 implements Actionbar{
    @Override
    public void sendActionbar(Player p, String message) {

        IChatBaseComponent icbc = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + message + "\"}");
        PacketPlayOutChat bar = new PacketPlayOutChat(icbc, ChatMessageType.CHAT);

        ((CraftPlayer) p).getHandle().playerConnection.sendPacket(bar);
    }
}
