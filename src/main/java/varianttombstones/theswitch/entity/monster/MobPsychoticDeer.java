package varianttombstones.theswitch.entity.monster;

import net.minecraft.core.WeightedRandomLootObject;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.entity.EntityDispatcher;
import net.minecraft.core.entity.animal.MobPig;
import net.minecraft.core.entity.monster.MobMonster;
import net.minecraft.core.entity.monster.MobZombiePig;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.item.Items;
import net.minecraft.core.util.helper.MathHelper;
import net.minecraft.core.world.World;
import net.minecraft.core.world.pos.TilePos;
import net.minecraft.core.world.season.Season;
import net.minecraft.core.world.season.Seasons;
import org.jetbrains.annotations.NotNull;
import varianttombstones.theswitch.TheSwitch;

public class MobPsychoticDeer extends MobAnimalPsychotic {
	public float lookFadeAlpha = 1.0F;

	public MobPsychoticDeer(@NotNull World world) {
		super(world);

		this.setTextureIdentifier("minecraft", "deer");
		this.setSize(0.9F, 1.3F);
		this.mentallyStableEntity = EntityDispatcher.getInstance().FACTORY_DEER;
		this.moveSpeed = 0.8F;
		this.mobDrops.add(new WeightedRandomLootObject(Items.FOOD_VENISON_RAW.getDefaultStack(), 1, 2));
		this.jumpHeight = (double)0.5F;
	}

	public String getLivingSound() {
		return "";
	}

	protected String getHurtSound() {
		return "";
	}

	protected String getDeathSound() {
		return "";
	}

	protected float getSoundVolume() {
		return 0.4F;
	}


	public int getMaxSpawnedInChunk() {
		Season currentSeason = this.world.getSeasonManager().getCurrentSeason();
		return currentSeason != Seasons.OVERWORLD_WINTER && currentSeason != Seasons.OVERWORLD_WINTER_ENDLESS ? 0 : 4;
	}
}
