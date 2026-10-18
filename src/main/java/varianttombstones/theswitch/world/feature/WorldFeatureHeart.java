package varianttombstones.theswitch.world.feature;

import net.minecraft.client.Minecraft;
import net.minecraft.core.block.Blocks;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.world.World;
import net.minecraft.core.world.generate.feature.WorldFeature;
import net.minecraft.core.world.pos.TilePos;
import net.minecraft.core.world.pos.TilePosc;
import varianttombstones.theswitch.block.ModBlocks;

import java.util.Random;

public class WorldFeatureHeart extends WorldFeature {
	@Override
	public boolean place(World world, Random random, int x, int y, int z) {
		if (random.nextInt(1500) != 1)
		{
			return false;
		}

		byte height = 10;
		int width = 10;
		int length = 10;

		for (int currentX = x - width - 1; currentX <= x + width + 1; ++currentX) {
			for (int currentY = y - 1; currentY <= y + height + 1; ++currentY) {
				for (int currentZ = z - length - 1; currentZ <= z + length + 1; ++currentZ) {
					Material material = world.getBlockMaterial(currentX, currentY, currentZ);

					if (currentY == y - 1 && !material.isSolid()) {
						return false;
					}

					if (currentY == y + height + 1 && !material.isSolid()) {
						return false;
					}
				}
			}
		}

		generateWalls(world, width, length, height, x, y, z);
		generateHeart(world, width, length, height, x, y, z);

		return true;
	}

	private void generateWalls(World world, int width, int length, int height, int x, int y, int z)
	{
		for (int positionX = -1; positionX <= width + 1; positionX++)
		{
			for (int positionZ = -1; positionZ <= length + 1; positionZ++)
			{
				for (int positionY = -1; positionY <= height + 1; positionY++)
				{
					world.setBlockWithNotify(x + positionX, y + positionY, z + positionZ, Blocks.AIR.id());
					if (positionX == -1 || positionX == width + 1) {
						world.setBlockWithNotify(x + positionX, y + positionY, z + positionZ, ModBlocks.FLESH.id());
					}

					if (positionZ == -1 || positionZ == length + 1) {
						world.setBlockWithNotify(x + positionX, y + positionY, z + positionZ, ModBlocks.FLESH.id());
					}

					if (positionY == -1 || positionY == height + 1) {
						world.setBlockWithNotify(x + positionX, y + positionY, z + positionZ, ModBlocks.FLESH.id());
					}
				}
			}
		}
	}

	private void generateHeart(World world, int width, int length, int height, int x, int y, int z)
	{
		int widthQuarter = width / 4;
		int lengthQuarter = length / 4;
		int heightQuarter = height / 4;

		int startHeight = heightQuarter;
		Random random = new Random();

		if (random.nextBoolean())
		{
			startHeight = 0;
		} else {
			startHeight = height / 2;
		}

		int endHeight = startHeight + (height / 2);

		TilePos root = new TilePos(widthQuarter, startHeight, lengthQuarter);

		for (int positionX = widthQuarter; positionX <= (width / 2) + widthQuarter; positionX++)
		{
			for (int positionZ = lengthQuarter; positionZ <= (length / 2) + lengthQuarter; positionZ++)
			{
				for (int positionY = startHeight; positionY <= endHeight; positionY++)
				{
					TilePos tilePos = new TilePos(positionX, positionY, positionZ);
					if (!isHeartCorner(tilePos, width / 2, length / 2,
						height / 2, root)) {
						world.setBlockWithNotify(x + positionX, y + positionY, z + positionZ, ModBlocks.HEART_FLESH.id());
					}
				}
			}
		}
	}

	private boolean isHeartCorner(TilePos tilePos, int width, int length, int height, TilePos root)
	{
		if (tilePos.x == root.x && tilePos.y == root.y && tilePos.z == root.z)
		{
			return true;
		}

		if (tilePos.x == root.x + width && tilePos.y == root.y + height && tilePos.z == root.z + length)
		{
			return true;
		}

		if (tilePos.x == root.x && tilePos.y == root.y && tilePos.z == root.z + length)
		{
			return true;
		}

		if (tilePos.x == root.x && tilePos.y == root.y + height && tilePos.z == root.z)
		{
			return true;
		}

		if (tilePos.x == root.x + width && tilePos.y == root.y && tilePos.z == root.z)
		{
			return true;
		}

		if (tilePos.x == root.x + width && tilePos.y == root.y + height && tilePos.z == root.z)
		{
			return true;
		}

		if (tilePos.x == root.x + width && tilePos.y == root.y && tilePos.z == root.z + length)
		{
			return true;
		}

		if (tilePos.x == root.x && tilePos.y == root.y + height && tilePos.z == root.z + length)
		{
			return true;
		}

		return false;
	}
}
