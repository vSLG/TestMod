package vslg.testmod.common.events;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface TreeChopCallback {
    Event<TreeChopCallback> EVENT =
            EventFactory.createArrayBacked(TreeChopCallback.class,
                    (listeners) -> (pos, world, miner, stack) -> {
                        for (TreeChopCallback listener : listeners) {
                            ActionResult result =
                                    listener.interact(pos, world, miner, stack);
                            if (result != ActionResult.PASS)
                                return result;
                        }
                        return ActionResult.PASS;
                    });

    ActionResult interact(BlockPos pos, World world, LivingEntity miner,
            ItemStack stack);
}
