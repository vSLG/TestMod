package vslg.testmod.common;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModBlocks {
    public static void registerAll() {
        for (Ore ore : Ore.values()) {
            register(ore.getName() + "_block", ore.getBlock());
            register(ore.getName() + "_ore", ore.getOre());
        }
    }

    private static <T extends Block> T register(String name, T block) {
        Identifier id = new Identifier(TestMod.MODID, name);
        Registry.register(Registry.BLOCK, id, block);
        ModItems.BLOCKS_TO_REGISTER.put(name, new BlockItem(block,
                new Item.Settings().group(TestMod.ITEM_GROUP)));
        return block;
    }
}
