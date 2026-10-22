package varianttombstones.theswitch.entity.monster;

import net.minecraft.core.WeightedRandomLootObject;
import net.minecraft.core.entity.Entity;
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

		this.moveSpeed = 0.8F;
		this.scoreValue = 200;
		this.stepDownSize = 0.5F;
		this.mobDrops.add(new WeightedRandomLootObject(Items.FOOD_PORKCHOP_RAW.getDefaultStack(), 1, 2));
	}

	protected void updateAI()
	{
		super.updateAI();
		if (world.getBlockBiome(new TilePos(this.x, this.y, this.z)) != TheSwitch.THE_SWITCH_BIOME && !this.world.isClientSide) {
			MobPig pig = new MobPig(this.world);
			pig.moveTo(this.x, this.y, this.z, this.yRot, this.xRot);
			pig.setHealthRaw(this.getHealth());
			pig.setSkinVariant(this.getSkinVariant());
			this.world.entityJoinedWorld(pig);
			this.remove();
			if (this.passenger != null) {
				this.passenger.startRiding(pig);
			}
		}
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
