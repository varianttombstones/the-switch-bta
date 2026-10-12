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

public class MobPsychoticPig extends MobMonster {
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

	protected Entity findPlayerToAttack() {
		double d = (double)10.0F;
		Player p = this.world.getClosestPlayerToEntity(this, d);
		return p != null && !p.gamemode.hasHostileMobs() ? null : p;

		/* Spider logic, might be cool to apply here
		float f = this.calcBrightness(1.0F);
		if (f < 0.5F) {
			double d = (double)16.0F;
			Player p = this.world.getClosestPlayerToEntity(this, d);
			return p != null && !p.gamemode.hasHostileMobs() ? null : p;
		} else {
			return null;
		}
		*/
	}

	protected void updateAI()
	{
		super.updateAI();
		if (world.getBlockBiome(new TilePos(this.x, this.y, this.z)) != TheSwitch.THE_SWITCH_BIOME && !this.world.isClientSide) {
			MobPig pig = new MobPig(this.world);
			pig.moveTo(this.x, this.y, this.z, this.yRot, this.xRot);
			pig.setHealthRaw(this.getHealth());
			this.world.entityJoinedWorld(pig);
			this.remove();
			if (this.passenger != null) {
				this.passenger.startRiding(pig);
			}
		}
	}

	protected void attackEntity(@NotNull Entity entity, float distance) {
		/* More spider stuff
		float brightness = this.calcBrightness(1.0F);
		if (brightness > 0.5F && this.random.nextInt(100) == 0) {
			this.target = null;
		} else {

		}
		*/

		if (distance > 2.0F && distance < 6.0F && this.random.nextInt(10) == 0) {
			if (this.onGround) {
				double d = entity.x - this.x;
				double d1 = entity.z - this.z;
				float f2 = MathHelper.sqrt(d * d + d1 * d1);
				this.xd = d / (double)f2 * (double)0.5F * (double)0.8F + this.xd * 0.2;
				this.zd = d1 / (double)f2 * (double)0.5F * (double)0.8F + this.zd * 0.2;
				this.yd = 0.4F;
			}
		} else {
			super.attackEntity(entity, distance);
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
