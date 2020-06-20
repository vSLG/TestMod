package vslg.testmod.mixin;

import org.spongepowered.asm.mixin.Mixin;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import vslg.testmod.common.ToolHelper;
import vslg.testmod.common.events.TreeChopCallback;

@Mixin(AxeItem.class)
public class TestAxeMixin {
    public boolean postMine(ItemStack stack, World world, BlockState state,
            BlockPos pos, LivingEntity miner) {
        if (!world.isClient && state.getHardness(world, pos) != 0.0F) {
            stack.damage(1, (LivingEntity) miner, ((e) -> {
                e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND);
            }));

            if (ToolHelper.LOG_BLOCKS.contains(state.getBlock()))
                TreeChopCallback.EVENT.invoker().interact(pos, world, miner,
                        stack);
        }

        return true;
    }
}
