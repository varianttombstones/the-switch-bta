package varianttombstones.theswitch.mixin.entity;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.core.entity.Mob;
import net.minecraft.core.entity.animal.MobAnimal;
import net.minecraft.core.entity.animal.MobPig;
import net.minecraft.core.world.World;
import net.minecraft.core.world.pos.TilePos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import varianttombstones.theswitch.TheSwitch;
import varianttombstones.theswitch.entity.monster.MobPsychoticPig;

@Mixin(MobPig.class)
public class MobPigMixin extends MobAnimal {
	public MobPigMixin(World world) {
		super(world);
	}

	@Inject(
		method="updateAI",
		at=@At("TAIL")
	)
	protected void addSwitchTransformation(CallbackInfo ci)
	{
		if (this.world.getBlockBiome(new TilePos(this.x, this.y, this.z)) == TheSwitch.THE_SWITCH_BIOME && !this.world.isClientSide) {
			MobPsychoticPig psychoPig = new MobPsychoticPig(this.world);
			psychoPig.moveTo(this.x, this.y, this.z, this.yRot, this.xRot);
			this.world.entityJoinedWorld(psychoPig);
			this.remove();
			if (this.passenger != null) {
				this.passenger.startRiding(psychoPig);
			}
		}
	}
}
