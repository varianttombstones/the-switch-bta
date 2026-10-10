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
		if (random.nextInt(1500) != 1)
		{
			return false;
		}

		byte height = 4;
		int width = 3;
		int length = 3;

		for (int currentX = x - width - 1; currentX <= x + width + 1; ++currentX) {
			for (int currentY = y - 1; currentY <= y + height + 1; ++currentY) {
				for (int currentZ = z - length - 1; currentZ <= z + length + 1; ++currentZ) {
					Material material = world.getBlockMaterial(currentX, currentY, currentZ);

					if (currentY == y - 1 && !material.isSolid()) {
						return false;
					}

					if (currentY == y + height + 1 && material.isSolid()) {
						return false;
					}

					if (currentY == y - 1 && world.getBlockType(new TilePos(x, y, z)) != ModBlocks.LIVING_GRASS)
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

		for (int positionY = 0; positionY <= height + 1; positionY++)
		{
			world.setBlockWithNotify(x, y + positionY, z, rand.nextBoolean() ? Blocks.COBBLE_STONE.id() : Blocks.COBBLE_STONE_MOSSY.id());

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
}
