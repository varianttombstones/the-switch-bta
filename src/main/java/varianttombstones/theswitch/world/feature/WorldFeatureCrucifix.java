package varianttombstones.theswitch.world.feature;

import net.minecraft.core.block.Blocks;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.world.World;
import net.minecraft.core.world.generate.feature.WorldFeature;
import net.minecraft.core.world.pos.TilePos;
import varianttombstones.theswitch.block.ModBlocks;

import java.util.Random;

public class WorldFeatureCrucifix extends WorldFeature {
	@Override
	public boolean place(World world, Random random, int x, int y, int z) {
		byte height = 4;
		int width = 3;
		int length = 3;

		if (world.getBlockType(new TilePos(x, y - 1, z)) != ModBlocks.LIVING_GRASS)
		{
			return false;
		}

		for (int currentY = y; currentY <= height - 1; currentY++)
		{
			for (int currentX = x  - 1; currentX <= x + 1; ++currentX)
			{
				for (int currentZ = z - 1; currentZ <= z + 1; ++currentZ)
				{
					Material material = world.getBlockMaterial(new TilePos(currentX, currentY, currentZ));
					if (material.isSolid())
					{
						return false;
					}
				}
			}
		}

		generateCross(world, width, length, height, x, y, z);

		return true;
	}

	private void generateCross(World world, int width, int length, int height, int x, int y, int z)
	{
		Random rand = new Random();
		boolean facingZ = rand.nextBoolean();

		for (int positionY = 0; positionY <= height - 1; positionY++)
		{
			world.setBlockWithNotify(x, y + positionY, z, rand.nextBoolean() ? Blocks.COBBLE_STONE.id() : Blocks.COBBLE_STONE_MOSSY.id());

			if (positionY == 2)
			{
				if (facingZ)
				{
					world.setBlockWithNotify(x - 1, y + positionY, z, rand.nextBoolean() ? Blocks.COBBLE_STONE.id() : Blocks.COBBLE_STONE_MOSSY.id());
					world.setBlockWithNotify(x + 1, y + positionY, z, rand.nextBoolean() ? Blocks.COBBLE_STONE.id() : Blocks.COBBLE_STONE_MOSSY.id());
				} else {
					world.setBlockWithNotify(x, y + positionY, z - 1, rand.nextBoolean() ? Blocks.COBBLE_STONE.id() : Blocks.COBBLE_STONE_MOSSY.id());
					world.setBlockWithNotify(x, y + positionY, z + 1, rand.nextBoolean() ? Blocks.COBBLE_STONE.id() : Blocks.COBBLE_STONE_MOSSY.id());
				}
			}
		}

//		for (int positionY = height + 1; positionY <= 200; positionY++)
//		{
//			world.setBlockWithNotify(x, y + positionY, z, Blocks.GLASS.id());
//		}
	}
}
