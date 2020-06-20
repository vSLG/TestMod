package vslg.testmod.common;

import java.util.LinkedList;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import vslg.testmod.common.events.TreeChopCallback;

public class ModEvents {
    public static void registerAll() {
        TreeChopCallback.EVENT.register(ModEvents::treeChopCallback);
    }

    private static ActionResult treeChopCallback(BlockPos pos, World world,
            LivingEntity miner, ItemStack stack) {
        LinkedList<BlockPos> logBlocks = ToolHelper.getTreeLogs(pos, world);
        int allowedDamage = Math.min(
                stack.getMaxDamage() - stack.getDamage() - 1, logBlocks.size());

        for (int i = 0; i < allowedDamage; i++) {
            world.breakBlock(logBlocks.get(i), true);
        }

        stack.damage(allowedDamage, miner, ((e) -> {
            e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND);
        }));

        return ActionResult.SUCCESS;
    }
}
