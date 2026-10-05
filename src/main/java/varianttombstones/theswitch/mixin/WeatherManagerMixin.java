package varianttombstones.theswitch.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.core.world.World;
import net.minecraft.core.world.pos.TilePos;
import net.minecraft.core.world.season.Seasons;
import net.minecraft.core.world.weather.Weather;
import net.minecraft.core.world.weather.WeatherManager;
import net.minecraft.core.world.weather.Weathers;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import varianttombstones.theswitch.TheSwitch;

@Mixin(WeatherManager.class)
public class WeatherManagerMixin {
	@Shadow
	@Final
	private @NotNull World world;

	@Inject(
		method = "tick",
		at = @At("HEAD")
	)
	private void setBloodRain(CallbackInfoReturnable<Boolean> cir)
	{
		Minecraft mc = Minecraft.getMinecraft();
		WeatherManager wm = mc.currentWorld.getWeatherManager();

		if (mc.currentWorld.getWorldType().hasCeiling()) {
			cir.setReturnValue(false);
		} else if (mc.currentWorld.getSeasonManager().getCurrentSeason() == null) {
			cir.setReturnValue(false);
		} else {
			TilePos cameraTile = mc.activeCamera.getTilePos();
			if (mc.currentWorld.getBlockBiome(cameraTile) == TheSwitch.THE_SWITCH_BIOME) {
				if (shouldBloodRain(wm))
				{
					wm.overrideWeather(Weathers.OVERWORLD_RAIN_BLOOD);
				}
			}
			else
			{
				if (wm.getCurrentWeather() == Weathers.OVERWORLD_RAIN_BLOOD) {
					if (world.getSeasonManager().getCurrentSeason() == Seasons.OVERWORLD_WINTER)
					{
						wm.overrideWeather(Weathers.OVERWORLD_WINTER_SNOW);
					}else{
						wm.overrideWeather(Weathers.OVERWORLD_RAIN);
					}
				}
			}
		}
	}

	private boolean shouldBloodRain(@NotNull WeatherManager wm)
	{
		Weather currentWeather = wm.getCurrentWeather();
		return currentWeather == Weathers.OVERWORLD_RAIN ||
			currentWeather == Weathers.OVERWORLD_STORM ||
			currentWeather == Weathers.OVERWORLD_SNOW ||
			currentWeather == Weathers.OVERWORLD_WINTER_SNOW;
	}
}
