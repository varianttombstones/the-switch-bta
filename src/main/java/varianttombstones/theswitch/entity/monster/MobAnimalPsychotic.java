package varianttombstones.theswitch.entity.monster;

import net.minecraft.core.block.Blocks;
import net.minecraft.core.block.tag.BlockTags;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.entity.Mob;
import net.minecraft.core.entity.animal.MobPig;
import net.minecraft.core.entity.factories.EntityFactory;
import net.minecraft.core.entity.monster.MobMonster;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.util.helper.MathHelper;
import net.minecraft.core.world.World;
import net.minecraft.core.world.pos.TilePos;
import net.minecraft.core.world.season.Season;
import net.minecraft.core.world.season.Seasons;
import org.jetbrains.annotations.NotNull;
import varianttombstones.theswitch.TheSwitch;

public class MobAnimalPsychotic extends MobMonster
{
	protected EntityFactory<?> mentallyStableEntity;

	public MobAnimalPsychotic(@NotNull World world) {
		super(world);
	}

	protected Entity findPlayerToAttack() {
		double d = (double) 10.0F;
		Player p = this.world.getClosestPlayerToEntity(this, d);
		return p != null && !p.gamemode.hasHostileMobs() ? null : p;
	}

	protected void attackEntity(@NotNull Entity entity, float distance) {
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

	protected void updateAI()
	{
		super.updateAI();
		if (world.getBlockBiome(new TilePos(this.x, this.y, this.z)) != TheSwitch.THE_SWITCH_BIOME && !this.world.isClientSide) {
			TransformSane();
		}
	}

	public boolean canSpawnHere() {
		int x = MathHelper.floor(this.x);
		int y = MathHelper.floor(this.bb.minY);
		int z = MathHelper.floor(this.z);
		int id = this.world.getBlockId(x, y - 1, z);
		if (Blocks.blocksList[id] == null) {
			return false;
		} else {
			return Blocks.blocksList[id].hasTag(BlockTags.PASSIVE_MOBS_SPAWN) && this.world.getFullBlockLightValue(x, y, z) > 8 && super.canSpawnHere();
		}
	}

	public int getMaxSpawnedInChunk() {
		Season currentSeason = this.world.getSeasonManager().getCurrentSeason();
		if (currentSeason == Seasons.OVERWORLD_SPRING) {
			return 8;
		} else if (currentSeason == Seasons.OVERWORLD_WINTER) {
			return 0;
		} else {
			return currentSeason == Seasons.OVERWORLD_WINTER_ENDLESS ? 1 : 4;
		}
	}

	public void TransformSane()
	{
		Mob stableMobInstance = (Mob) mentallyStableEntity.createEntity(this.world);
		stableMobInstance.moveTo(this.x, this.y, this.z, this.yRot, this.xRot);
		stableMobInstance.setHealthRaw(this.getHealth());
		stableMobInstance.setSkinVariant(this.getSkinVariant());

		if (this.hadNicknameSet)
		{
			stableMobInstance.setNickname(this.nickname);
		}

		if (stableMobInstance instanceof MobPig && this instanceof MobPsychoticPig)
		{
			((MobPig) stableMobInstance).setSaddled(((MobPsychoticPig) this).getSaddled());
		}

		this.world.entityJoinedWorld(stableMobInstance);
		this.remove();
	}
}
