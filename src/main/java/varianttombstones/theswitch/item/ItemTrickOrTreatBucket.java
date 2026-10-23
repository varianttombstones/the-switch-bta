package varianttombstones.theswitch.item;

import net.minecraft.core.entity.player.Player;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.Items;
import net.minecraft.core.util.collection.NamespaceID;
import net.minecraft.core.world.World;
import net.minecraft.core.world.pos.TilePos;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ItemTrickOrTreatBucket extends Item {
	public boolean filled = false;
	public boolean actionBuffer = false; // This exists so that it doesnt active immediately on fill.

	public ItemTrickOrTreatBucket(@NotNull String translationKey, @NotNull String namespaceId, int id) {
		super(translationKey, namespaceId, id);
	}

	public @Nullable ItemStack onUse(@NotNull ItemStack selfStack, @NotNull World world, @NotNull Player player) {
		/* Test code for just shoving the item into the inventory.
		if (this.filled) {
			ItemStack randomItem = getRandomItem();
			boolean inserted = false;
			for(int i = 0; i < player.inventory.mainInventory.length; ++i) {
				if (player.inventory.mainInventory[i] == null) {
					player.inventory.mainInventory[i] = randomItem;
					inserted = true;
					break;
				} else {
					if (player.inventory.mainInventory[i].getItem() == randomItem.getItem())
					{
						player.inventory.mainInventory[i].stackSize += randomItem.stackSize;
						inserted = true;
						break;
					}
				}
			}
			if (!inserted)
			{
				world.dropItem(new TilePos(player.x, player.y, player.z), randomItem);
			}

			this.filled = false;
		}else{
			this.filled = true;
		}
		*/

		if (!this.filled)
		{
			return selfStack;
		}

		if (!actionBuffer)
		{
			actionBuffer = true;
			return selfStack;
		}

		world.dropItem(new TilePos(player.x, player.y, player.z), getRandomItem());
		this.filled = false;
		this.actionBuffer = false;

		return selfStack;
	}

	private ItemStack getRandomItem() {
		return new ItemStack(Items.FOOD_PORKCHOP_COOKED);
	}
}
