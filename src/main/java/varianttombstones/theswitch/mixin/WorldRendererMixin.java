package varianttombstones.theswitch.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.option.GameSettings;
import net.minecraft.client.option.enums.ParticlesQuality;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.core.world.World;
import net.minecraft.core.world.weather.IPrecipitation;
import net.minecraft.core.world.weather.Weather;
import net.minecraft.core.world.weather.Weathers;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WorldRenderer.class)
public class WorldRendererMixin {
	@Inject(
		method = "addRainParticles",
		at = @At("HEAD"),
		cancellable = true
	)
	private void stopBloodRainGroundParticles(CallbackInfo ci)
	{
		if (GameSettings.PARTICLES_QUALITY.value != ParticlesQuality.NONE) {
			Minecraft mc = Minecraft.getMinecraft();
			World world = mc.currentWorld;
			if (world != null) {
				Weather weather = world.getCurrentWeather();
				if (weather instanceof IPrecipitation) {
					if (weather == Weathers.OVERWORLD_RAIN_BLOOD)
					{
						ci.cancel();
					}
				}
			}
		}

	}
}
