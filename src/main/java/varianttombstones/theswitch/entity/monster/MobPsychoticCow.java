package varianttombstones.theswitch.entity.monster;

import net.minecraft.core.WeightedRandomLootObject;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.entity.animal.MobCow;
import net.minecraft.core.entity.monster.MobMonster;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.item.Items;
import net.minecraft.core.util.helper.MathHelper;
import net.minecraft.core.world.World;
import net.minecraft.core.world.pos.TilePos;
import org.jetbrains.annotations.NotNull;
import varianttombstones.theswitch.TheSwitch;

public class MobPsychoticCow extends MobAnimalPsychotic {
	public float lookFadeAlpha = 1.0F;

	public MobPsychoticCow(@NotNull World world) {
		super(world);

		this.setTextureIdentifier("minecraft", "cow");
		this.setSize(0.9F, 1.3F);
		this.mobDrops.add(new WeightedRandomLootObject(Items.LEATHER.getDefaultStack(), 1, 5));
	}

	protected void updateAI()
	{
		super.updateAI();
		if (world.getBlockBiome(new TilePos(this.x, this.y, this.z)) != TheSwitch.THE_SWITCH_BIOME && !this.world.isClientSide) {
			MobCow cow = new MobCow(this.world);
			cow.moveTo(this.x, this.y, this.z, this.yRot, this.xRot);
			cow.setHealthRaw(this.getHealth());
			this.world.entityJoinedWorld(cow);
			this.remove();
		}
	}

	public String getLivingSound() {
		return "mob.cow";
	}

	protected String getHurtSound() {
		return "mob.cow";
	}

	protected String getDeathSound() {
		return "mob.cowdeath";
	}
}
