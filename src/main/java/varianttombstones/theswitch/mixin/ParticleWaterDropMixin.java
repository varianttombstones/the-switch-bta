package varianttombstones.theswitch.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.render.particle.Particle;
import net.minecraft.client.render.particle.ParticleTextureCache;
import net.minecraft.client.render.particle.ParticleWaterDrop;
import net.minecraft.core.world.Dimension;
import net.minecraft.core.world.World;
import net.minecraft.core.world.biome.Biome;
import net.minecraft.core.world.pos.TilePos;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import varianttombstones.theswitch.TheSwitch;

/*

* Not added to mixin json because the blood droplots dont fit the idea of
* the blood rising into the sky.
* If it is to be re-added, the rain particle override in world renderer
* needs to be commented out.

 */
@Mixin(ParticleWaterDrop.class)
public class ParticleWaterDropMixin extends Particle {
	public ParticleWaterDropMixin(@NotNull World world, double x, double y, double z, double xa, double ya, double za) {
		super(world, x, y, z, xa, ya, za);
	}

	@Inject(
		method = "<init>",
		at =  @At("RETURN")
	)
	private void setBloodDropletsSwitch(World world, double x, double y, double z, Dimension dimension, CallbackInfo ci)
	{
		Minecraft mc = Minecraft.getMinecraft();
		TilePos cameraPos = mc.activeCamera.getTilePos();

		Biome biome = world.getBlockBiome(cameraPos);
		if (biome == TheSwitch.THE_SWITCH_BIOME) {
			tex = ParticleTextureCache.ICON_SPLASH_RAIN_BLOOD[random.nextInt(4)];
		}
	}
}
