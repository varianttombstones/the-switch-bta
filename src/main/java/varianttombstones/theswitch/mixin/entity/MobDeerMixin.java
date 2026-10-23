package varianttombstones.theswitch.mixin.entity;

import net.minecraft.core.entity.animal.MobAnimal;
import net.minecraft.core.entity.animal.MobDeer;
import net.minecraft.core.world.World;
import net.minecraft.core.world.pos.TilePos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import varianttombstones.theswitch.TheSwitch;
import varianttombstones.theswitch.entity.monster.MobPsychoticDeer;

@Mixin(MobDeer.class)
public abstract class MobDeerMixin extends MobAnimal {
	public MobDeerMixin(World world) {
		super(world);
	}

	@Inject(
		method="updateAI",
		at=@At("TAIL")
	)
	protected void addSwitchTransformation(CallbackInfo ci)
	{
		if (this.world.getBlockBiome(new TilePos(this.x, this.y, this.z)) == TheSwitch.THE_SWITCH_BIOME && !this.world.isClientSide) {
			MobPsychoticDeer psychoDeer = new MobPsychoticDeer(this.world);
			psychoDeer.moveTo(this.x, this.y, this.z, this.yRot, this.xRot);
			psychoDeer.setSkinVariant(this.getSkinVariant());
			this.world.entityJoinedWorld(psychoDeer);
			this.remove();
		}
	}
}
