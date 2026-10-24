package varianttombstones.theswitch.entity.monster;

import com.mojang.nbt.tags.CompoundTag;
import net.minecraft.core.WeightedRandomLootObject;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.entity.EntityDispatcher;
import net.minecraft.core.entity.Mob;
import net.minecraft.core.entity.animal.MobPig;
import net.minecraft.core.entity.monster.MobMonster;
import net.minecraft.core.entity.monster.MobZombiePig;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.item.Items;
import net.minecraft.core.util.helper.MathHelper;
import net.minecraft.core.world.World;
import net.minecraft.core.world.pos.TilePos;
import org.jetbrains.annotations.NotNull;
import varianttombstones.theswitch.TheSwitch;

public class MobPsychoticPig extends MobAnimalPsychotic {
	public float lookFadeAlpha = 1.0F;

	public MobPsychoticPig(@NotNull World world) {
		super(world);

		this.setTextureIdentifier("minecraft", "pig");
		this.setSize(0.9F, 0.9F);
		this.mentallyStableEntity = EntityDispatcher.getInstance().FACTORY_PIG;

		this.moveSpeed = 0.8F;
		this.scoreValue = 200;
		this.stepDownSize = 0.5F;
		this.mobDrops.add(new WeightedRandomLootObject(Items.FOOD_PORKCHOP_RAW.getDefaultStack(), 1, 2));
	}

	protected void defineSynchedData() {
		this.entityData.define(16, (byte)0, Byte.class);
	}

	public boolean getSaddled() {
		return (this.entityData.getByte(16) & 1) != 0;
	}

	public void setSaddled(boolean flag) {
		if (flag) {
			this.entityData.set(16, (byte)1);
		} else {
			this.entityData.set(16, (byte)0);
		}
	}

	protected void dropDeathItems() {
		if (this.getSaddled()) {
			this.dropItem(Items.SADDLE.id, 1);
			this.setSaddled(false);
		}

		super.dropDeathItems();
	}

	public void addAdditionalSaveData(@NotNull CompoundTag tag) {
		super.addAdditionalSaveData(tag);
		tag.putBoolean("Saddle", this.getSaddled());
	}

	public void readAdditionalSaveData(@NotNull CompoundTag tag) {
		super.readAdditionalSaveData(tag);
		this.setSaddled(tag.getBoolean("Saddle"));
	}


	public String getLivingSound() {
			return "mob.pig";
		}

	protected String getHurtSound() {
		return "mob.pig";
	}

	protected String getDeathSound() {
		return "mob.pigdeath";
	}
}
