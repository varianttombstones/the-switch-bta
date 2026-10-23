package varianttombstones.theswitch.item;

import net.minecraft.client.render.item.model.ItemModelDispatcher;
import varianttombstones.theswitch.item.model.ItemModelTrickOrTreatBucket;

public class ModItemsRenderer {
	public static void RegisterRenderers(ItemModelDispatcher dispatcher)
	{
		dispatcher.addDispatch(new ItemModelTrickOrTreatBucket(ModItems.TRICK_OR_TREAT_BUCKET));
	}
}
