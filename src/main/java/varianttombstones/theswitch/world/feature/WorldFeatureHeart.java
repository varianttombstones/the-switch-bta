package varianttombstones.theswitch.world.feature;

import net.minecraft.core.block.Blocks;
import net.minecraft.core.world.World;
import net.minecraft.core.world.generate.feature.WorldFeature;
import net.minecraft.core.world.pos.TilePosc;
import varianttombstones.theswitch.block.ModBlocks;

import java.util.Random;

public class WorldFeatureHeart extends WorldFeature {
	@Override
	public boolean place(World world, Random random, int x, int y, int z) {
		world.setBlockWithNotify(x, y, z, Blocks.GLASS.id());
		return false;
	}
}
