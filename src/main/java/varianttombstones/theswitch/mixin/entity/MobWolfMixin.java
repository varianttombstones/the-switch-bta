package varianttombstones.theswitch.mixin.entity;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.core.entity.Mob;
import net.minecraft.core.entity.animal.MobAnimal;
import net.minecraft.core.entity.animal.MobPig;
import net.minecraft.core.entity.animal.MobWolf;
import net.minecraft.core.world.World;
import net.minecraft.core.world.pos.TilePos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import varianttombstones.theswitch.TheSwitch;
import varianttombstones.theswitch.entity.monster.MobPsychoticPig;

import java.util.Random;

@Mixin(MobWolf.class)
public abstract class MobWolfMixin extends MobAnimal {
	@Shadow
	public abstract void whine();

	public MobWolfMixin(World world) {
		super(world);
	}

	@Inject(
		method="updateAI",
		at=@At("TAIL")
	)
	protected void whineInSwitch(CallbackInfo ci)
	{
		Random rand = new Random();
		if (rand.nextInt(20000) < 10)
		{
			if (this.world.getBlockBiome(new TilePos(this.x, this.y, this.z)) == TheSwitch.THE_SWITCH_BIOME
				&& !this.world.isClientSide) {
				whine();
			}
		}
	}
}
