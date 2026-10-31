package varianttombstones.theswitch;

import net.minecraft.core.block.Blocks;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.Items;
import turniplabs.halplibe.helper.RecipeBuilder;
import varianttombstones.theswitch.block.ModBlocks;
import varianttombstones.theswitch.item.ModItems;

public class ModRecipes {
	public static void initializeRecipes()
	{
		RecipeBuilder.Shaped(TheSwitch.MOD_ID)
			.setShape("yyy","yzy","#x#")
			.addInput('x', ModItems.BLOOD_PEARL)
			.addInput('y', Items.CHAINLINK)
			.addInput('z', ModItems.ECTOPLASM)
			.create("sanitycharm", new ItemStack(ModItems.SANITY_CHARM, 1));

		RecipeBuilder.Shaped(TheSwitch.MOD_ID)
			.setShape("yyy","yxy","yyy")
			.addInput('x', Items.BUCKET_IRON)
			.addInput('y', ModItems.ECTOPLASM)
			.create("trickortreatbucket", new ItemStack(ModItems.TRICK_OR_TREAT_BUCKET, 1));

		RecipeBuilder.Shaped(TheSwitch.MOD_ID)
			.setShape("yyy","yxy","yyy")
			.addInput('x', ModItems.BLOOD_PEARL)
			.addInput('y', ModItems.ECTOPLASM)
			.create("ghostorb", new ItemStack(ModItems.GHOST_ORB, 1));

		RecipeBuilder.Shaped(TheSwitch.MOD_ID)
			.setShape("x")
			.addInput('x', ModBlocks.HAUNTED_LOG)
			.create("wood", new ItemStack(Blocks.PLANKS_OAK_PAINTED, 4));

		RecipeBuilder.Shaped(TheSwitch.MOD_ID)
			.setShape("x")
			.addInput('x', ModBlocks.SEEING_LOG)
			.create("wood2", new ItemStack(Blocks.PLANKS_OAK_PAINTED, 4));

	}
}
