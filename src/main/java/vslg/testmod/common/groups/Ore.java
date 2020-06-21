package vslg.testmod.common.groups;

import java.util.Locale;
import java.util.function.Supplier;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.AxeItem;
import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.Lazy;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import vslg.testmod.common.block.ModOreBlock;
import vslg.testmod.common.TestMod;

public enum Ore {
    RUBY(ModMaterial.RUBY);

    private final Lazy<Block> block;
    private final Lazy<Item> item;
    private final Lazy<ModOreBlock> ore;

    private final Lazy<SwordItem> sword;
    private final Lazy<ShovelItem> shovel;
    private final Lazy<PickaxeItem> pickaxe;
    private final Lazy<AxeItem> axe;
    private final Lazy<HoeItem> hoe;

    Ore(ModMaterial material) {
        block = new Lazy<>(() -> new Block(
                FabricBlockSettings.of(Material.METAL).hardness(3.f)));
        item = new Lazy<>(
                () -> new Item(new Item.Settings().group(TestMod.ITEM_GROUP)));
        ore = new Lazy<>(() -> new ModOreBlock());

        sword = new Lazy<>(() -> new SwordItem(material, 3, -2.4F,
                new Item.Settings().group(TestMod.ITEM_GROUP)));
        shovel = new Lazy<>(() -> new ShovelItem(material, 1.5F, -3.0F,
                new Item.Settings().group(TestMod.ITEM_GROUP)));
        pickaxe = new Lazy<>(() -> new ModPickaxe(material, 1, -2.8F,
                new Item.Settings().group(TestMod.ITEM_GROUP)));
        axe = new Lazy<>(() -> new ModAxe(material, 6.0F, -3.0F,
                new Item.Settings().group(TestMod.ITEM_GROUP)));
        hoe = new Lazy<>(() -> new HoeItem(material, -3.0F,
                new Item.Settings().group(TestMod.ITEM_GROUP)));
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

    public SwordItem getSword() {
        return sword.get();
    }

    public ShovelItem getShovel() {
        return shovel.get();
    }

    public PickaxeItem getPickaxe() {
        return pickaxe.get();
    }

    public AxeItem getAxe() {
        return axe.get();
    }

    public HoeItem getHoe() {
        return hoe.get();
    }

    public static void handleBiome(Biome biome) {
        if (!(biome.getCategory() != Biome.Category.NETHER
                && biome.getCategory() != Biome.Category.THEEND))
            return;

        biome.addFeature(GenerationStep.Feature.UNDERGROUND_ORES,
                Feature.ORE
                        .configure(new OreFeatureConfig(
                                OreFeatureConfig.Target.NATURAL_STONE,
                                Ore.RUBY.getOre().getDefaultState(), 8))
                        .createDecoratedFeature(Decorator.COUNT_RANGE.configure(
                                new RangeDecoratorConfig(6, 0, 0, 24))));
    }

    public enum ModMaterial implements ToolMaterial {
        RUBY(0, 1024, 12.0F, 5.0F, 22, () -> {
            return Ingredient.ofItems(Ore.RUBY.getItem());
        });

        private final int miningLevel;
        private final int itemDurability;
        private final float miningSpeed;
        private final float attackDamage;
        private final int enchantability;
        private final Lazy<Ingredient> repairIngredient;

        private ModMaterial(int miningLevel, int itemDurability,
                float miningSpeed, float attackDamage, int enchantability,
                Supplier<Ingredient> repairIngredient) {
            this.miningLevel = miningLevel;
            this.itemDurability = itemDurability;
            this.miningSpeed = miningSpeed;
            this.attackDamage = attackDamage;
            this.enchantability = enchantability;
            this.repairIngredient = new Lazy<>(repairIngredient);
        }

        @Override
        public int getDurability() {
            return this.itemDurability;
        }

        @Override
        public float getMiningSpeed() {
            return this.miningSpeed;
        }

        @Override
        public float getAttackDamage() {
            return this.attackDamage;
        }

        @Override
        public int getMiningLevel() {
            return this.miningLevel;
        }

        @Override
        public int getEnchantability() {
            return this.enchantability;
        }

        @Override
        public Ingredient getRepairIngredient() {
            return this.repairIngredient.get();
        }
    }

    public class ModPickaxe extends PickaxeItem {
        public ModPickaxe(ToolMaterial material, int attackDamage,
                float attackSpeed, Settings settings) {
            super(material, attackDamage, attackSpeed, settings);
        }
    }

    public class ModAxe extends AxeItem {
        public ModAxe(ToolMaterial material, float attackDamage,
                float attackSpeed, Settings settings) {
            super(material, attackDamage, attackSpeed, settings);
        }
    }
}
