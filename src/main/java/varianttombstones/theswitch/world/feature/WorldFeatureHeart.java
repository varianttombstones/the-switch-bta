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
					TilePos blockTargetPosition = new TilePos(x + positionX, y + positionY, z + positionZ);
					world.setBlockTypeNotify(blockTargetPosition, Blocks.AIR);
					if (positionX == -1 || positionX == width + 1) {
						world.setBlockTypeNotify(blockTargetPosition, ModBlocks.FLESH);
					}

					if (positionZ == -1 || positionZ == length + 1) {
						world.setBlockTypeNotify(blockTargetPosition, ModBlocks.FLESH);
					}

					if (positionY == -1 || positionY == height + 1) {
						world.setBlockTypeNotify(blockTargetPosition, ModBlocks.FLESH);
					}
				}
			}
		}
	}

	private void generateHeart(World world, int width, int length, int height, int x, int y, int z)
	{
		int widthQuarter = width / 4;
		int lengthQuarter = length / 4;

		int startHeight = 0;
		Random random = new Random();

		if (random.nextBoolean())
		{
			startHeight = height / 2;
		}

		int endWidth = (width / 2) + widthQuarter;
		int endLength = (length / 2) + lengthQuarter;
		int endHeight = startHeight + (height / 2);

		TilePos root = new TilePos(widthQuarter, startHeight, lengthQuarter);

		for (int positionX = widthQuarter; positionX <= endWidth; positionX++)
		{
			for (int positionZ = lengthQuarter; positionZ <= endLength; positionZ++)
			{
				for (int positionY = startHeight; positionY <= endHeight; positionY++)
				{
					TilePos tilePos = new TilePos(positionX, positionY, positionZ);
					TilePos blockTargetPosition = new TilePos(x + positionX, y + positionY, z + positionZ);
					if (!isHeartCorner(tilePos, width / 2, length / 2,
						height / 2, root)) {
						world.setBlockTypeNotify(blockTargetPosition, ModBlocks.HEART_FLESH);
					}
				}
			}
		}

		// Place the core
		int randomX = random.nextInt(widthQuarter + 1, endWidth);
		int randomY = random.nextInt(startHeight, endHeight);
		int randomZ = random.nextInt(lengthQuarter + 1, endLength);
		TilePos corePosition = new TilePos(x + randomX, y + randomY, z + randomZ);
		world.setBlockTypeNotify(corePosition, ModBlocks.SWITCH_CORE);
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
