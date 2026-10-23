package varianttombstones.theswitch.entity.monster;

import com.mojang.nbt.tags.CompoundTag;
import net.minecraft.core.WeightedRandomLootObject;
import net.minecraft.core.block.Blocks;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.entity.EntityDispatcher;
import net.minecraft.core.entity.EntityItem;
import net.minecraft.core.entity.animal.MobPig;
import net.minecraft.core.entity.monster.MobMonster;
import net.minecraft.core.entity.monster.MobZombiePig;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.Items;
import net.minecraft.core.item.tool.ItemToolShears;
import net.minecraft.core.util.helper.DyeColor;
import net.minecraft.core.util.helper.MathHelper;
import net.minecraft.core.world.World;
import net.minecraft.core.world.pos.TilePos;
import org.jetbrains.annotations.NotNull;
import varianttombstones.theswitch.TheSwitch;

import java.util.Random;

public class MobPsychoticSheep extends MobAnimalPsychotic {
	public static final int DATA_WOOL_STATE_ID = 16;
	public static final int MASK_WOOL_COLOR = 15;
	public static final int MASK_SHEARED = 16;
	public static final int DATA_EATING_ID = 17;

	private int growthTimer;

	public MobPsychoticSheep(@NotNull World world) {
		super(world);

		this.setTextureIdentifier("minecraft", "sheep");
		this.setSize(0.9F, 1.3F);
		this.mentallyStableEntity = EntityDispatcher.getInstance().FACTORY_SHEEP;
	}

	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(16, (byte)0, Byte.class);
		this.entityData.define(17, (byte)0, Byte.class);
	}

	public void spawnInit() {
		this.setFleeceColor(getRandomFleeceColor(this.random));
	}


	protected void dropDeathItems() {
		super.dropDeathItems();
		if (!this.getSheared()) {
			this.dropItem(new ItemStack(Blocks.WOOL.id(), 1, this.getFleeceColor().blockMeta), 0.0F);
		}
	}


	public boolean interact(@NotNull Player player) {
		if (super.interact(player)) {
			return true;
		} else {
			ItemStack itemstack = player.inventory.getCurrentItem();
			if (itemstack != null && itemstack.getItem() instanceof ItemToolShears && !this.getSheared()) {
				if (!this.world.isClientSide) {
					this.setSheared(true);
					int count = 2 + this.random.nextInt(3);

					for(int j = 0; j < count; ++j) {
						EntityItem entityitem = this.dropItem(new ItemStack(Blocks.WOOL.id(), 1, this.getFleeceColor().blockMeta), 1.0F);
						entityitem.yd += (double)(this.random.nextFloat() * 0.05F);
						entityitem.xd += (double)((this.random.nextFloat() - this.random.nextFloat()) * 0.1F);
						entityitem.zd += (double)((this.random.nextFloat() - this.random.nextFloat()) * 0.1F);
					}
				}

				itemstack.damageItem(1, player);
				if (itemstack.stackSize <= 0) {
					player.destroyCurrentEquippedItem();
				}

				return true;
			} else {
				return false;
			}
		}
	}

	public void onItemInteract(ItemStack itemStack) {
		if (itemStack.getItem() instanceof ItemToolShears && !this.getSheared()) {
			if (!this.world.isClientSide) {
				this.setSheared(true);
				int count = 2 + this.random.nextInt(3);

				for(int j = 0; j < count; ++j) {
					EntityItem entityItem = this.dropItem(new ItemStack(Blocks.WOOL.id(), 1, this.getFleeceColor().blockMeta), 1.0F);
					entityItem.yd += (double)(this.random.nextFloat() * 0.05F);
					entityItem.xd += (double)((this.random.nextFloat() - this.random.nextFloat()) * 0.1F);
					entityItem.zd += (double)((this.random.nextFloat() - this.random.nextFloat()) * 0.1F);
				}
			}

			itemStack.damageItem(1, (Entity)null);
		}

	}

	public void addAdditionalSaveData(@NotNull CompoundTag tag) {
		super.addAdditionalSaveData(tag);
		tag.putBoolean("Sheared", this.getSheared());
		tag.putByte("Color", (byte)this.getFleeceColor().blockMeta);
		tag.putShort("GrowthTimer", (short)this.growthTimer);
	}

	public void readAdditionalSaveData(@NotNull CompoundTag tag) {
		super.readAdditionalSaveData(tag);
		this.setSheared(tag.getBoolean("Sheared"));
		this.setFleeceColor(DyeColor.colorFromBlockMeta(tag.getByte("Color")));
		this.setGrowthTimer(tag.getShort("GrowthTimer"));
	}


	public DyeColor getFleeceColor() {
		return DyeColor.colorFromBlockMeta(this.entityData.getByte(16) & 15);
	}

	public void setFleeceColor(DyeColor color) {
		byte woolState = this.entityData.getByte(16);
		this.entityData.set(16, (byte)(woolState & -16 | color.blockMeta & 15));
	}

	public boolean getSheared() {
		return (this.entityData.getByte(16) & 16) != 0;
	}

	public void setSheared(boolean flag) {
		byte woolState = this.entityData.getByte(16);
		if (flag) {
			this.entityData.set(16, (byte)(woolState | 16));
		} else {
			this.entityData.set(16, (byte)(woolState & -17));
		}

	}

	public static DyeColor getRandomFleeceColor(Random random) {
		int i = random.nextInt(100);
		if (i < 5) {
			return DyeColor.BLACK;
		} else if (i < 10) {
			return DyeColor.GRAY;
		} else if (i < 15) {
			return DyeColor.SILVER;
		} else if (i < 18) {
			return DyeColor.BROWN;
		} else {
			return random.nextInt(500) != 0 ? DyeColor.WHITE : DyeColor.PINK;
		}
	}

	public void setGrowthTimer(int growthTimer) {
		this.growthTimer = growthTimer;
	}

	public String getLivingSound() {
		return "mob.sheep";
	}

	protected String getHurtSound() {
		return "mob.sheep";
	}

	protected String getDeathSound() {
		return "mob.sheepdeath";
	}
}
