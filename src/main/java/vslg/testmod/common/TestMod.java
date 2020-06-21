package vslg.testmod.common;

import vslg.testmod.common.groups.Ore;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.event.registry.RegistryEntryAddedCallback;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class TestMod implements ModInitializer {
	public static final String MODID = "testmod";

	public static final ItemGroup ITEM_GROUP =
			FabricItemGroupBuilder.build(new Identifier(MODID, "items"),
					() -> new ItemStack(Ore.RUBY.getItem()));

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		System.out.println("Hello Fabric world!");
		ModBlocks.registerAll();
		ModItems.registerAll();
		ModEvents.registerAll();

		// Add ore to biomes
		Registry.BIOME.forEach(Ore::handleBiome);

		// Listen to new biomes being registered
		RegistryEntryAddedCallback.event(Registry.BIOME)
				.register((i, identifier, biome) -> Ore.handleBiome(biome));
	}
}
