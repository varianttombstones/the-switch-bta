package varianttombstones.theswitch.item;

import net.minecraft.core.item.Item;
import turniplabs.halplibe.helper.ItemBuilder;
import turniplabs.halplibe.helper.creativeInventory.CreativeInventoryCategory;
import turniplabs.halplibe.helper.creativeInventory.CreativeInventoryPlacement;
import varianttombstones.theswitch.TheSwitch;

public class ModItems {
	public static int ITEM_ID = 992;

	public static Item TRICK_OR_TREAT_BUCKET;

	public static void InitItems() {
		TRICK_OR_TREAT_BUCKET = new ItemBuilder(TheSwitch.MOD_ID)
			.setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.MISCELLANEOUS))
			.build(new ItemTrickOrTreatBucket("trickortreatbucket",
				"theswitch:item/trickortreatbucket",
				newItemID()));
	}

	public static int newItemID() {
		ITEM_ID = ITEM_ID + 1;
		return ITEM_ID;
	}
}
