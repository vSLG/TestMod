package vslg.testmod.common;

import java.util.LinkedHashMap;
import java.util.Map;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModItems {
    public static final Map<String, BlockItem> BLOCKS_TO_REGISTER =
            new LinkedHashMap<>();

    public static void registerAll() {
        BLOCKS_TO_REGISTER.forEach(ModItems::register);

        for (Ore ore : Ore.values())
            register(ore.getName(), ore.getItem());
    }

    private static <T extends Item> T register(String name, T item) {
        Identifier id = new Identifier(TestMod.MODID, name);
        Registry.register(Registry.ITEM, id, item);
        return item;
    }
}
