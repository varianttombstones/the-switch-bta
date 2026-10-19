package varianttombstones.theswitch.block;

import net.minecraft.core.block.*;
import net.minecraft.core.block.material.Materials;
import net.minecraft.core.block.tag.BlockTags;
import net.minecraft.core.sound.BlockSounds;
import turniplabs.halplibe.helper.BlockBuilder;
import turniplabs.halplibe.helper.creativeInventory.CreativeInventoryCategory;
import turniplabs.halplibe.helper.creativeInventory.CreativeInventoryPlacement;
import varianttombstones.theswitch.TheSwitch;
import varianttombstones.theswitch.block.logic.BlockLogicFlesh;

public class ModBlocks {
	private static int BLOCK_ID = 2344; // REPLACE ME TO NOT CONFLICT WITH BWF

	public static Block<?> LIVING_DIRT;
	public static Block<?> LIVING_GRASS;
	public static Block<?> HAUNTED_LOG;
	public static Block<?> SEEING_LOG;
	public static Block<?> HAUNTED_LEAVES;
	public static Block<?> FLESH;
	public static Block<?> HEART_FLESH;
	public static Block<?> DEAD_FLOWERS;
	public static Block<?> SWITCH_CORE; // MAYBE RENAME AS HEART CORE?

	public static void InitBlocks() {
		LIVING_DIRT = new BlockBuilder(TheSwitch.MOD_ID)
			.setResistance(.5f)
			.setHardness(.5f)
			.setBlockSound(BlockSounds.GRAVEL)
			.setTags(BlockTags.MINEABLE_BY_SWORD, BlockTags.GROWS_TREES, BlockTags.GROWS_FLOWERS)
			.setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.ORGANIC))
			.build("livingdirt", newBlockID(), b -> new BlockLogic(b, Materials.DIRT));

		LIVING_GRASS = new BlockBuilder(TheSwitch.MOD_ID)
			.setResistance(.5f)
			.setHardness(.5f)
			.setBlockSound(BlockSounds.GRAVEL)
			.setTags(BlockTags.MINEABLE_BY_SWORD, BlockTags.GROWS_TREES, BlockTags.GROWS_FLOWERS)
			.setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.ORGANIC))
			.build("livinggrass", newBlockID(), b -> new BlockLogic(b, Materials.GRASS));

		HAUNTED_LOG = new BlockBuilder(TheSwitch.MOD_ID)
			.setResistance(2f)
			.setHardness(2f)
			.setBlockSound(BlockSounds.WOOD)
			.setTags(BlockTags.MINEABLE_BY_AXE)
			.setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.ORGANIC))
			.build("hauntedlog", newBlockID(), b -> new BlockLogicLog(b));

		SEEING_LOG = new BlockBuilder(TheSwitch.MOD_ID)
			.setResistance(2f)
			.setHardness(2f)
			.setBlockSound(BlockSounds.WOOD)
			.setTags(BlockTags.MINEABLE_BY_AXE)
			.setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.ORGANIC))
			.build("seeinglog", newBlockID(), b -> new BlockLogicLog(b));

		HAUNTED_LEAVES = new BlockBuilder(TheSwitch.MOD_ID)
			.setResistance(0.2f)
			.setHardness(0.2f)
			.setBlockSound(BlockSounds.GRASS)
			.setTags(BlockTags.MINEABLE_BY_AXE, BlockTags.MINEABLE_BY_HOE, BlockTags.MINEABLE_BY_SWORD, BlockTags.MINEABLE_BY_SHEARS, BlockTags.SHEARS_DO_SILK_TOUCH)
			.setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.ORGANIC))
			.build("hauntedleaves", newBlockID(), b -> new BlockLogicLeavesBase(b, Materials.LEAVES, Blocks.SAPLING_OAK)); // TODO: REPLACE SAPLING

		FLESH = new BlockBuilder(TheSwitch.MOD_ID)
			.setResistance(6f)
			.setHardness(1.5f)
			.setBlockSound(BlockSounds.GRAVEL)
			.setTags(BlockTags.MINEABLE_BY_PICKAXE)
			.setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.ORGANIC))
			.build("flesh", newBlockID(), b -> new BlockLogicFlesh(b, Materials.DIRT));

		HEART_FLESH = new BlockBuilder(TheSwitch.MOD_ID)
			.setResistance(6f)
			.setHardness(1.5f)
			.setBlockSound(BlockSounds.GRAVEL)
			.setTags(BlockTags.MINEABLE_BY_PICKAXE)
			.setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.ORGANIC))
			.build("heartflesh", newBlockID(), b -> new BlockLogic(b, Materials.DIRT));

		DEAD_FLOWERS = new BlockBuilder(TheSwitch.MOD_ID)
			.setResistance(0f)
			.setHardness(0f)
			.setBlockSound(BlockSounds.GRASS)
			.setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.ORGANIC))
			.build("deadflowers", newBlockID(), b -> new BlockLogicFlowerStackable(b));

		SWITCH_CORE = new BlockBuilder(TheSwitch.MOD_ID)
			.setResistance(6f)
			.setHardness(1.5f)
			.setBlockSound(BlockSounds.GRAVEL)
			.setTags(BlockTags.MINEABLE_BY_PICKAXE)
			.setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.ORGANIC))
			.build("switchcore", newBlockID(), b -> new BlockLogic(b, Materials.DIRT));
	}

	public static int newBlockID() {
		BLOCK_ID = BLOCK_ID + 1;
		return BLOCK_ID;
	}
}
