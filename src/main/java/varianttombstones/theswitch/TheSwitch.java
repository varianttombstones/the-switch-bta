package varianttombstones.theswitch;

import net.fabricmc.api.ModInitializer;
import net.minecraft.client.render.block.model.BlockModelDispatcher;
import net.minecraft.core.world.biome.Biome;
import net.minecraft.core.world.biome.BiomeTags;
import net.minecraft.core.world.biome.Biomes;
import net.minecraft.core.world.weather.Weather;
import net.minecraft.core.world.weather.Weathers;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import turniplabs.halplibe.HalpLibe;
import turniplabs.halplibe.event.defs.ClientEvents;
import turniplabs.halplibe.event.defs.CommonEvents;
import turniplabs.halplibe.util.dependency.Key;
import varianttombstones.theswitch.block.BlockRenderer;
import varianttombstones.theswitch.block.ModBlocks;
import varianttombstones.theswitch.world.biome.TheSwitchBiome;

public class TheSwitch implements ModInitializer {
	public static final String MOD_ID = HalpLibe.registerMod("theswitch", true);
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static Biome THE_SWITCH_BIOME;

	@Override
	public void onInitialize() {
		CommonEvents.BEFORE_GAME_START.listen(Key.of(MOD_ID), this::beforeGameStart);
		CommonEvents.AFTER_GAME_START.listen(Key.of(MOD_ID), this::afterGameStart);
		/*
		CommonEvents.RECIPES_NAMESPACE_INIT.listen(Key.of(MOD_ID), this::initNamespaces);
		CommonEvents.RECIPES_READY.listen(Key.of(MOD_ID), this::onRecipesReady);
		*/
		if (HalpLibe.isClient)
		{
			ClientEvents.BLOCK_MODEL_RELOAD.listen(Key.of(MOD_ID),(t)-> BlockRenderer.RegisterRenderers(BlockModelDispatcher.getInstance()));
		}

		LOGGER.info("The Switch initialized.");
	}

	public void beforeGameStart() {
		ModBlocks.InitBlocks();
		THE_SWITCH_BIOME = Biomes.register("theswitch:overworld.theswitch", new TheSwitchBiome("overworld.theswitch")
			.withDebugColor(11184810).withPlacementDefaults(0.625F, 0.25F, .5F) // Change variety in future
			.withTags(BiomeTags.HAS_SURFACE_WATER)
		);
	}

	public void afterGameStart() {

	}
}
