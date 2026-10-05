package varianttombstones.theswitch.block;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogic;
import net.minecraft.core.block.material.Materials;
import net.minecraft.core.block.tag.BlockTags;
import net.minecraft.core.sound.BlockSounds;
import turniplabs.halplibe.helper.BlockBuilder;
import turniplabs.halplibe.helper.creativeInventory.CreativeInventoryCategory;
import turniplabs.halplibe.helper.creativeInventory.CreativeInventoryPlacement;
import varianttombstones.theswitch.TheSwitch;

public class ModBlocks {
	private static int BLOCK_ID = 2344; // REPLACE ME TO NOT CONFLICT WITH BWF

	public static Block<?> LIVING_DIRT;
	public static Block<?> LIVING_GRASS;

	public static void InitBlocks() {
		LIVING_DIRT = new BlockBuilder(TheSwitch.MOD_ID)
			.setResistance(.5f)
			.setHardness(.5f)
			.setBlockSound(BlockSounds.GRAVEL)
			.setTags(BlockTags.MINEABLE_BY_SWORD)
			.setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.ORGANIC))
			.build("livingdirt", newBlockID(), b -> new BlockLogic(b, Materials.DIRT));

		LIVING_GRASS = new BlockBuilder(TheSwitch.MOD_ID)
			.setResistance(.5f)
			.setHardness(.5f)
			.setBlockSound(BlockSounds.GRAVEL)
			.setTags(BlockTags.MINEABLE_BY_SWORD, BlockTags.GROWS_TREES)
			.setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.ORGANIC))
			.build("livinggrass", newBlockID(), b -> new BlockLogic(b, Materials.GRASS));

	}

	public static int newBlockID() {
		BLOCK_ID = BLOCK_ID + 1;
		return BLOCK_ID;
	}
}
