package varianttombstones.theswitch.item.model;

import net.minecraft.client.render.item.model.ItemModelStandard;
import net.minecraft.client.render.texture.stitcher.IconCoordinate;
import net.minecraft.client.render.texture.stitcher.TextureRegistry;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import varianttombstones.theswitch.item.ItemTrickOrTreatBucket;

public class ItemModelTrickOrTreatBucket extends ItemModelStandard {
	protected IconCoordinate empty = TextureRegistry.getTexture("theswitch:item/trickortreatbucket/empty");
	protected IconCoordinate full = TextureRegistry.getTexture("theswitch:item/trickortreatbucket/filled");

	public ItemModelTrickOrTreatBucket(@NotNull Item item) {
		super(item);
	}

	public @NotNull IconCoordinate getIcon(@Nullable Entity entity, @NotNull ItemStack itemStack) {
		ItemTrickOrTreatBucket bucket = (ItemTrickOrTreatBucket) itemStack.getItem();
		return itemStack.getData().getBoolean("Filled") ? this.full : this.empty;
	}
}
