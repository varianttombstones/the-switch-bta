package varianttombstones.theswitch.world.biome;

import net.minecraft.core.block.Blocks;
import net.minecraft.core.world.biome.Biome;
import net.minecraft.core.world.biome.SurfaceProperties;
import net.minecraft.core.world.generate.feature.WorldFeature;
import net.minecraft.core.world.generate.feature.tree.WorldFeatureTree;
import net.minecraft.core.world.generate.feature.tree.WorldFeatureTreeTall;
import net.minecraft.core.world.weather.Weather;
import net.minecraft.core.world.weather.Weathers;
import org.jetbrains.annotations.NotNull;
import varianttombstones.theswitch.block.ModBlocks;

import java.util.Random;

public class TheSwitchBiome extends Biome {
	public TheSwitchBiome(String key)
	{
		super(key);
		this.spawnableMonsterList.clear();
		this.spawnableCreatureList.clear();
		this.spawnableWaterCreatureList.clear();
		this.spawnableAmbientCreatureList.clear();

		this.withSurfaceProperties(new SurfaceProperties.Builder()
			.withTopBlock(ModBlocks.LIVING_GRASS)
			.withFillerBlock(ModBlocks.LIVING_DIRT)
			.build()
		);

		for (Weather weather : Weathers.WEATHERS)
		{
			if (weather != Weathers.OVERWORLD_RAIN_BLOOD)
			{
				assert weather != null;
				this.blockedWeathers.add(weather);
			}
		}
	}

	@Override
	public int getSkyColor(float temperature) {
		return SKY_COLOR;
	}

	private static final int SKY_COLOR = 0xACADC2;

	@Override
	public @NotNull WorldFeature getTreeFeature(@NotNull Random random) {
		return random.nextInt(10) == 0 ? new WorldFeatureTreeTall(Blocks.LEAVES_THORN.id(), Blocks.LOG_THORN.id()) : new WorldFeatureTree(Blocks.LEAVES_THORN.id(), Blocks.LOG_THORN.id(), 8);
	}
}
