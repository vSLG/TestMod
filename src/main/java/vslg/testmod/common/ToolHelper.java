package vslg.testmod.common;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ToolHelper {
    public static final Set<Block> LOG_BLOCKS =
            new HashSet<Block>(Arrays.asList(new Block[] {Blocks.OAK_LOG,
                    Blocks.SPRUCE_LOG, Blocks.BIRCH_LOG, Blocks.JUNGLE_LOG,
                    Blocks.ACACIA_LOG, Blocks.DARK_OAK_LOG,
                    Blocks.STRIPPED_OAK_LOG, Blocks.STRIPPED_SPRUCE_LOG,
                    Blocks.STRIPPED_BIRCH_LOG, Blocks.STRIPPED_JUNGLE_LOG,
                    Blocks.STRIPPED_ACACIA_LOG, Blocks.STRIPPED_DARK_OAK_LOG}));

    public static LinkedList<BlockPos> getTreeLogs(BlockPos start,
            World world) {
        LinkedList<BlockPos> blockList = new LinkedList<BlockPos>();
        BlockPos current = null, currentLog = null;
        int idx = 0;

        blockList.add(start);

        do {
            currentLog = blockList.get(idx++);

            for (int y = 0; y <= 1; y++)
                for (int x = -1; x <= 1; x++)
                    for (int z = -1; z <= 1; z++) {
                        current = currentLog.add(x, y, z);
                        if (LOG_BLOCKS.contains(
                                world.getBlockState(current).getBlock())
                                && !blockList.contains(current)) {
                            blockList.add(current);
                        }
                    }
        } while (idx < blockList.size());

        return blockList;
    }
}

