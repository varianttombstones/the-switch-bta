package varianttombstones.theswitch.block;

import net.minecraft.client.render.block.model.BlockModelDispatcher;
import net.minecraft.client.render.block.model.generic.BlockModelGeneric;
import net.minecraft.client.render.block.model.generic.BlockModelGenericAxis;
import net.minecraft.client.render.block.model.generic.BlockModelGenericLeaves;
import net.minecraft.core.block.Blocks;

public class BlockRenderer {
	public static void RegisterRenderers(BlockModelDispatcher dispatcher)
	{
		dispatcher.addDispatch(new BlockModelGeneric<>(ModBlocks.LIVING_DIRT, BlockModelDispatcher.loadDataModel("theswitch:block/living_dirt")));
		dispatcher.addDispatch(new BlockModelGeneric<>(ModBlocks.LIVING_GRASS, BlockModelDispatcher.loadDataModel("theswitch:block/living_grass")));
		dispatcher.addDispatch(new BlockModelGenericAxis<>(ModBlocks.HAUNTED_LOG, BlockModelDispatcher.loadDataModel("theswitch:block/haunted_log")));
		dispatcher.addDispatch(new BlockModelGenericAxis<>(ModBlocks.SEEING_LOG, BlockModelDispatcher.loadDataModel("theswitch:block/seeing_log")));
		dispatcher.addDispatch(new BlockModelGenericLeaves<>(ModBlocks.HAUNTED_LEAVES, "theswitch:block/haunted_leaves"));
		dispatcher.addDispatch(new BlockModelGeneric<>(ModBlocks.FLESH, BlockModelDispatcher.loadDataModel("theswitch:block/flesh")));
	}
}
