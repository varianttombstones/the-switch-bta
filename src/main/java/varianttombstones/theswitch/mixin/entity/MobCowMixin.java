package varianttombstones.theswitch.mixin.entity;

import net.minecraft.core.entity.animal.MobAnimal;
import net.minecraft.core.entity.animal.MobCow;
import net.minecraft.core.world.World;
import net.minecraft.core.world.pos.TilePos;
import org.spongepowered.asm.mixin.Mixin;
import varianttombstones.theswitch.TheSwitch;
import varianttombstones.theswitch.entity.monster.MobPsychoticCow;

@Mixin(MobCow.class)
public class MobCowMixin extends MobAnimal {
	public MobCowMixin(World world) {
		super(world);
	}

	@Override
	protected void updateAI()
	{
		super.updateAI();
		if (this.world.getBlockBiome(new TilePos(this.x, this.y, this.z)) == TheSwitch.THE_SWITCH_BIOME && !this.world.isClientSide) {
			MobPsychoticCow psychoCow = new MobPsychoticCow(this.world);
			psychoCow.moveTo(this.x, this.y, this.z, this.yRot, this.xRot);
			psychoCow.setSkinVariant(this.getSkinVariant());
			this.world.entityJoinedWorld(psychoCow);
			this.remove();
		}
	}
}
