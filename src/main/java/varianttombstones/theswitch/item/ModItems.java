package varianttombstones.theswitch.item;

import net.minecraft.core.item.Item;
import turniplabs.halplibe.helper.ItemBuilder;
import turniplabs.halplibe.helper.creativeInventory.CreativeInventoryCategory;
import turniplabs.halplibe.helper.creativeInventory.CreativeInventoryPlacement;
import varianttombstones.theswitch.TheSwitch;

public class ModItems {
	public static int ITEM_ID = 19992;

	public static Item TRICK_OR_TREAT_BUCKET;
	public static Item BLOOD_PEARL;
	public static Item SANITY_CHARM;
	public static Item ECTOPLASM;

	public static void InitItems() {
		TRICK_OR_TREAT_BUCKET = new ItemBuilder(TheSwitch.MOD_ID)
			.setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.MISCELLANEOUS))
			.build(new ItemTrickOrTreatBucket("trickortreatbucket",
				"theswitch:item/trickortreatbucket",
				newItemID()));

		BLOOD_PEARL = new ItemBuilder(TheSwitch.MOD_ID)
			.setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.MISCELLANEOUS))
			.build(new Item("bloodpearl",
				"theswitch:item/bloodpearl",
				newItemID()));

		SANITY_CHARM = new ItemBuilder(TheSwitch.MOD_ID)
			.setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.MISCELLANEOUS))
			.build(new Item("sanitycharm",
				"theswitch:item/sanitycharm",
				newItemID()));

		ECTOPLASM = new ItemBuilder(TheSwitch.MOD_ID)
			.setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.MISCELLANEOUS))
			.build(new ItemSanityAmulet("theswitch:item/ectoplasm",
				"ectoplasm",
				newItemID()));
	}

	public static int newItemID() {
		ITEM_ID = ITEM_ID + 1;
		return ITEM_ID;
	}
}
