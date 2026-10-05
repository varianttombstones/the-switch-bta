package varianttombstones.theswitch.block;

import net.minecraft.client.render.block.model.BlockModelDispatcher;
import net.minecraft.client.render.block.model.generic.BlockModelGeneric;
import net.minecraft.core.block.Blocks;

public class BlockRenderer {
	public static void RegisterRenderers(BlockModelDispatcher dispatcher)
	{
		dispatcher.addDispatch(new BlockModelGeneric<>(ModBlocks.LIVING_DIRT, BlockModelDispatcher.loadDataModel("theswitch:block/living_dirt")));
		dispatcher.addDispatch(new BlockModelGeneric<>(ModBlocks.LIVING_GRASS, BlockModelDispatcher.loadDataModel("theswitch:block/living_grass")));
	}
}
