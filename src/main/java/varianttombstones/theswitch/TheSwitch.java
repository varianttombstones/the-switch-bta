package varianttombstones.theswitch;

import net.fabricmc.api.ModInitializer;
import net.minecraft.core.world.biome.Biome;
import net.minecraft.core.world.biome.BiomeTags;
import net.minecraft.core.world.biome.Biomes;
import net.minecraft.core.world.weather.Weather;
import net.minecraft.core.world.weather.Weathers;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import turniplabs.halplibe.HalpLibe;
import turniplabs.halplibe.event.defs.CommonEvents;
import turniplabs.halplibe.util.dependency.Key;
import varianttombstones.theswitch.world.biome.TheSwitchBiome;

public class TheSwitch implements ModInitializer {
	public static final String MOD_ID = HalpLibe.registerMod("theswitch", true);
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static Biome THE_SWITCH_BIOME;

	@Override
	public void onInitialize() {
		CommonEvents.BEFORE_GAME_START.listen(Key.of(MOD_ID), this::beforeGameStart);
		CommonEvents.AFTER_GAME_START.listen(Key.of(MOD_ID), this::afterGameStart);
		LOGGER.info("The Switch initialized.");
	}

	public void beforeGameStart() {
		THE_SWITCH_BIOME = Biomes.register("theswitch.overworld.theswitch", new TheSwitchBiome("overworld.theswitch")
			.withDebugColor(11184810).withPlacementDefaults(0.575F, 0.125F, 0.1F)
			.withTags(BiomeTags.HAS_SURFACE_WATER)
		);


	}

	public void afterGameStart() {

	}
}
