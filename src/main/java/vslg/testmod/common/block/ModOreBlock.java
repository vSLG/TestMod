package vslg.testmod.common.block;

import java.util.Random;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Material;
import net.minecraft.block.OreBlock;
import net.minecraft.util.math.MathHelper;

public class ModOreBlock extends OreBlock {
    public ModOreBlock() {
        super(FabricBlockSettings.of(Material.STONE).strength(3.0F, 3.0F));
    }

    @Override
    public int getExperienceWhenMined(Random random) {
        return MathHelper.nextInt(random, 3, 7);
    }
}
