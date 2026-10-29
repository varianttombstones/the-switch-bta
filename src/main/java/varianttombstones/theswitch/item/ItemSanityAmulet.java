package varianttombstones.theswitch.item;

import net.minecraft.core.enums.HumanArmorShape;
import net.minecraft.core.item.IArmorItem;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.material.ArmorMaterial;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ItemSanityAmulet extends Item implements IArmorItem<HumanArmorShape> {
	public ItemSanityAmulet(String namespaceId, @NotNull String translationKey, int id) {
		super(translationKey, namespaceId, id);
		this.setMaxStackSize(1);
		this.setMaxDamage(192);
	}

	public boolean showFullDurability() {
		return false;
	}

	public @Nullable ArmorMaterial getArmorMaterial() {
		return null;
	}

	public @NotNull HumanArmorShape getArmorShape() {
		return HumanArmorShape.CHEST;
	}
}
