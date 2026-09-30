package varianttombstones.theswitch.world.biome;

import net.minecraft.core.world.biome.Biome;
import net.minecraft.core.world.weather.Weather;
import net.minecraft.core.world.weather.Weathers;

public class TheSwitchBiome extends Biome {
	public TheSwitchBiome(String key)
	{
		super(key);
		this.spawnableMonsterList.clear();
		this.spawnableCreatureList.clear();
		this.spawnableWaterCreatureList.clear();
		this.spawnableAmbientCreatureList.clear();

		for (Weather weather : Weathers.WEATHERS)
		{
			if (weather != Weathers.OVERWORLD_RAIN_BLOOD)
			{
				assert weather != null;
				this.blockedWeathers.add(weather);
			}
		}
	}
}
