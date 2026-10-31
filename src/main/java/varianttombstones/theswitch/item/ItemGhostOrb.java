package varianttombstones.theswitch.item;

import net.minecraft.core.entity.player.Player;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;
import net.minecraft.core.world.pos.TilePosc;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import varianttombstones.theswitch.entity.ModEntities;
import varianttombstones.theswitch.entity.monster.MobSpirit;

public class ItemGhostOrb extends Item {
	public ItemGhostOrb(@NotNull String namespaceId, @NotNull String translationKey, int id) {
		super(translationKey, namespaceId, id);
	}

	public boolean onUseOnBlock(@NotNull ItemStack selfStack, @NotNull World world, @Nullable Player player, @NotNull TilePosc blockPos, @NotNull Side side, double xHit, double yHit) {
		if (player == null) {
			return false;
		} else if (player.isSneaking()) {
			return false;
		} else {
			if (!world.isClientSide) {
				double x = (double)blockPos.x() + (double)0.5F + (double)side.offsetX() * (double)0.5F;
				double y = (double)blockPos.y() + (double)0.5F + (double)side.offsetY() * (double)0.5F;
				double z = (double)blockPos.z() + (double)0.5F + (double)side.offsetZ() * (double)0.5F;
				this.spawnEntity(selfStack, world, x, y, z);
			}

			--selfStack.stackSize;
			return true;
		}
	}

	public void spawnEntity(@NotNull ItemStack itemStack, @NotNull World world, double x, double y, double z) {
		MobSpirit spirit = ModEntities.FACTORY_SPIRIT.createEntity(world);
		if (spirit != null) {
			spirit.setPos(x, y, z);
			spirit.spawnInit();
			String customName = itemStack.getCustomName();
			if (customName != null) {
				if (itemStack.hasCustomColor()) {
					spirit.chatColor = itemStack.getCustomColor();
				}

				spirit.setNickname(customName);
			}

			spirit.setPassive(true);
			world.entityJoinedWorld(spirit);
		}
	}
}
