package battleroyale.battleroyale.packets;

import com.google.gson.internal.Primitives;
import net.minecraft.server.v1_12_R1.*;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ProtocolProvider {
    private static ProtocolProvider protocolProvider = new ProtocolProvider();

    public static ProtocolProvider getProtocolProvider() {
        return protocolProvider;
    }

    private static Field b = getDeclaredField("b");
    private static Field a = getDeclaredField("a");
    private static Map<Class<?>, Object> dataWatcherSerializers;
    {
        dataWatcherSerializers = new HashMap<>();
        Field[] fields = DataWatcherRegistry.class.getFields();
        for (Field field : fields) {
            try {
                dataWatcherSerializers.put(getGenericType(field), field.get(null));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

    }
    private static Field getDeclaredField(String fieldName)  {
        try {
            Field field = PacketPlayOutEntityMetadata.class.getDeclaredField(fieldName);
            field.setAccessible(true);
            return field;
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return null;
    }
    private Class<?> getGenericType(Field field){
        ParameterizedType parameterizedType =  (ParameterizedType) field.getGenericType();
        Type type = parameterizedType.getActualTypeArguments()[0];
        if (type instanceof Class<?>){
            return (Class<?>) type;
        }
        return null;
    }
    private static <T> DataWatcherSerializer<T> getSerializer(T val){
        Object serializer = dataWatcherSerializers.get(val.getClass());
        if (serializer == null)
            serializer = dataWatcherSerializers.get(Primitives.wrap(val.getClass()));
        return serializer == null ? null : ((DataWatcherSerializer<T>) serializer);
    }
    private static <T> DataWatcherObject<T> getObject(T val, byte id){
        DataWatcherSerializer<T> dataWatcherSerializer = getSerializer(val);
        return dataWatcherSerializer == null ? null : dataWatcherSerializer.a(id);
    }
    private static PacketPlayOutEntityMetadata createEmptyPacket(Entity entity) throws IllegalAccessException {
        PacketPlayOutEntityMetadata packetPlayOutEntityMetadata = new PacketPlayOutEntityMetadata();
        a.set(packetPlayOutEntityMetadata, entity.getEntityId());
        return packetPlayOutEntityMetadata;
    }
    public static <T> void sendMetadataPacket(Player player, Entity entity, byte id, T value) throws IllegalAccessException {
        PacketPlayOutEntityMetadata packetPlayOutEntityMetadata = createEmptyPacket(entity);
        b.set(packetPlayOutEntityMetadata, Collections.singletonList(new DataWatcher.Item<>(getObject(value, id), value)));
        sendPacket(player, packetPlayOutEntityMetadata);
    }
    public static void sendPacket(Player player, Packet packet){
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
    }
}
