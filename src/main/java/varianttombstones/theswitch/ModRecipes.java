package varianttombstones.theswitch;

import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.Items;
import turniplabs.halplibe.helper.RecipeBuilder;
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
	}
}
