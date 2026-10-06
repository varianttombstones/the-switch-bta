package varianttombstones.theswitch.world.biome;

import net.minecraft.core.block.Blocks;
import net.minecraft.core.block.tag.BlockTags;
import net.minecraft.core.world.World;
import net.minecraft.core.world.generate.feature.MethodParametersAnnotation;
import net.minecraft.core.world.generate.feature.tree.WorldFeatureTree;
import varianttombstones.theswitch.block.ModBlocks;

import java.util.Random;

public class WorldFeatureTreeHaunted extends WorldFeatureTree {
	@MethodParametersAnnotation(
		names = {"leavesID", "logID", "heightMod"}
	)
	public WorldFeatureTreeHaunted(int leavesID, int logID, int heightMod) {
		super(leavesID, logID, heightMod);
	}

	@Override
	public boolean place(World world, Random random, int x, int y, int z) {
		int treeHeight = random.nextInt(3) + this.heightMod;
		boolean canSpawn = true;

		int idBelow = world.getBlockId(x, y - 1, z);
		if (idBelow != ModBlocks.LIVING_DIRT.id() && idBelow != ModBlocks.LIVING_GRASS.id())
		{
			canSpawn = false;
		}

		if (y >= 1 && y + treeHeight + 1 <= world.getHeightBlocks()) {
			for(int iy = y; iy <= y + 1 + treeHeight; ++iy) {
				byte treeRadius = 1;
				if (iy == y) {
					treeRadius = 0;
				}

				if (iy >= y + 1 + treeHeight - 2) {
					treeRadius = 2;
				}

				for(int ix = x - treeRadius; ix <= x + treeRadius && canSpawn; ++ix) {
					for(int iz = z - treeRadius; iz <= z + treeRadius && canSpawn; ++iz) {
						if (iy >= 0 && iy < world.getHeightBlocks()) {
							int blockId = world.getBlockId(ix, iy, iz);
							if (blockId != 0 && blockId != this.leavesID) {
								canSpawn = false;
							}
						} else {
							canSpawn = false;
						}
					}
				}
			}

			if (!canSpawn) {
				return false;
			} else {
				if (Blocks.hasTag(idBelow, BlockTags.GROWS_TREES) && y < world.getHeightBlocks() - treeHeight - 1) {
					onTreeGrown(world, x, y, z);
					world.setBlockWithNotify(x, y - 1, z, ModBlocks.LIVING_DIRT.id());

					for(int iy = y - 3 + treeHeight; iy <= y + treeHeight; ++iy) {
						int j2 = iy - (y + treeHeight);
						int i3 = 1 - j2 / 2;

						for(int ix = x - i3; ix <= x + i3; ++ix) {
							int l3 = ix - x;

							for(int iz = z - i3; iz <= z + i3; ++iz) {
								int j4 = iz - z;
								if ((Math.abs(l3) != i3 || Math.abs(j4) != i3 || random.nextInt(2) != 0 && j2 != 0) && canLeavesReplace(world, ix, iy, iz)) {
									this.placeLeaves(world, ix, iy, iz, random);
								}
							}
						}
					}

					for(int l1 = 0; l1 < treeHeight; ++l1) {
						int id = world.getBlockId(x, y + l1, z);
						if (id == 0 || this.isLeaf(id)) {
							placeLog(world, x, y + l1, z, random);
						}
					}

					return true;
				} else {
					return false;
				}
			}
		} else {
			return false;
		}
	}

	private void placeLog(World world, int x, int y, int z, Random rand) {
		int baseLog = this.logID;
		int eyeLog = ModBlocks.SEEING_LOG.id();

		if (rand.nextInt(0, 25) > 23) {
			world.setBlockWithNotify(x, y, z, eyeLog);
		} else {
			world.setBlockWithNotify(x, y, z, baseLog);
		}
	}
}
