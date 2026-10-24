package varianttombstones.theswitch.mixin.entity;

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
public abstract class MobPigMixin extends MobAnimal {
	@Shadow
	public abstract boolean getSaddled();

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
			psychoPig.setSkinVariant(this.getSkinVariant());
			psychoPig.setSaddled(this.getSaddled());

			if (this.hadNicknameSet)
			{
				psychoPig.setNickname(this.nickname);
			}

			this.world.entityJoinedWorld(psychoPig);
			this.remove();
			if (psychoPig.passenger != null) {
				psychoPig.ejectRider();
			}
		}
	}
}
