package vslg.testmod.common;

import java.util.Locale;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.Item;
import net.minecraft.util.Lazy;
import vslg.testmod.common.block.ModOreBlock;

public enum Ore {
    RUBY;

    private final Lazy<Block> block;
    private final Lazy<Item> item;
    private final Lazy<ModOreBlock> ore;

    Ore() {
        block = new Lazy<>(() -> new Block(
                FabricBlockSettings.of(Material.METAL).hardness(3.f)));
        item = new Lazy<>(
                () -> new Item(new Item.Settings().group(TestMod.ITEM_GROUP)));
        ore = new Lazy<>(() -> new ModOreBlock());
    }

    public String getName() {
        return name().toLowerCase(Locale.ROOT);
    }

    public Block getBlock() {
        return block.get();
    }

    public Item getItem() {
        return item.get();
    }

    public ModOreBlock getOre() {
        return ore.get();
    }
}
