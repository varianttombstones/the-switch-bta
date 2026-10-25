package varianttombstones.theswitch.world.biome;

import net.minecraft.core.entity.SpawnListEntry;
import net.minecraft.core.world.biome.Biome;
import net.minecraft.core.world.biome.SurfaceProperties;
import net.minecraft.core.world.generate.feature.WorldFeature;
import net.minecraft.core.world.generate.feature.tree.WorldFeatureTreeTall;
import net.minecraft.core.world.weather.Weather;
import net.minecraft.core.world.weather.Weathers;
import org.jetbrains.annotations.NotNull;
import varianttombstones.theswitch.block.ModBlocks;
import varianttombstones.theswitch.entity.monster.*;
import varianttombstones.theswitch.world.feature.WorldFeatureTreeHaunted;

import java.util.Random;

public class TheSwitchBiome extends Biome {
	public TheSwitchBiome(String key)
	{
		super(key);
		addCreatures();

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

	private void addCreatures()
	{
		/*
		this.spawnableMonsterList.clear();
		this.spawnableCreatureList.clear();
		this.spawnableWaterCreatureList.clear();
		this.spawnableAmbientCreatureList.clear();
		 */

		this.spawnableCreatureList.add(new SpawnListEntry(MobPsychoticPig.class, 102));
		this.spawnableCreatureList.add(new SpawnListEntry(MobPsychoticCow.class, 102));
		this.spawnableCreatureList.add(new SpawnListEntry(MobPsychoticSheep.class, 102));
		this.spawnableCreatureList.add(new SpawnListEntry(MobPsychoticDeer.class, 20));
		this.spawnableMonsterList.add(new SpawnListEntry(MobSpirit.class, 120));
	}

	@Override
	public int getSkyColor(float temperature) {
		return SKY_COLOR;
	}

	private static final int SKY_COLOR = 0xACADC2;

	@Override
	public @NotNull WorldFeature getTreeFeature(@NotNull Random random) {
		return random.nextInt(10) == 0 ? new WorldFeatureTreeTall(ModBlocks.HAUNTED_LEAVES.id(), ModBlocks.HAUNTED_LOG.id()) : new WorldFeatureTreeHaunted(ModBlocks.HAUNTED_LEAVES.id(), ModBlocks.HAUNTED_LOG.id(), 5);
	}
}
