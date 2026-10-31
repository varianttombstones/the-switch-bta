package varianttombstones.theswitch;

import net.fabricmc.api.ModInitializer;
import net.minecraft.client.render.EntityRendererDispatcher;
import net.minecraft.client.render.block.model.BlockModelDispatcher;
import net.minecraft.client.render.item.model.ItemModelDispatcher;
import net.minecraft.client.render.particle.ParticleDispatcher;
import net.minecraft.client.render.particle.ParticleEntry;
import net.minecraft.client.render.particle.ParticleFluidDrip;
import net.minecraft.client.render.texture.stitcher.TextureRegistry;
import net.minecraft.core.entity.EntityDispatcher;
import net.minecraft.core.sound.SoundTypes;
import net.minecraft.core.world.biome.Biome;
import net.minecraft.core.world.biome.BiomeTags;
import net.minecraft.core.world.biome.Biomes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import turniplabs.halplibe.HalpLibe;
import turniplabs.halplibe.event.defs.ClientEvents;
import turniplabs.halplibe.event.defs.CommonEvents;
import turniplabs.halplibe.util.dependency.Key;
import varianttombstones.theswitch.block.ModBlockRenderer;
import varianttombstones.theswitch.block.ModBlocks;
import varianttombstones.theswitch.entity.ModEntities;
import varianttombstones.theswitch.entity.ModEntityRenderer;
import varianttombstones.theswitch.item.ModItems;
import varianttombstones.theswitch.item.ModItemsRenderer;
import varianttombstones.theswitch.world.biome.TheSwitchBiome;

public class TheSwitch implements ModInitializer {
	public static final String MOD_ID = HalpLibe.registerMod("theswitch", true);
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static Biome THE_SWITCH_BIOME;

	@Override
	public void onInitialize() {
		CommonEvents.BEFORE_GAME_START.listen(Key.of(MOD_ID), this::beforeGameStart);
		CommonEvents.AFTER_GAME_START.listen(Key.of(MOD_ID), this::afterGameStart);
		//CommonEvents.RECIPES_NAMESPACE_INIT.listen(Key.of(MOD_ID), this::initNamespaces);
		CommonEvents.RECIPES_READY.listen(Key.of(MOD_ID), this::onRecipesReady);

		if (HalpLibe.isClient)
		{
			ClientEvents.ITEM_MODEL_RELOAD.listen(Key.of(MOD_ID), (t) -> ModItemsRenderer.RegisterRenderers(ItemModelDispatcher.getInstance()));
			ClientEvents.BLOCK_MODEL_RELOAD.listen(Key.of(MOD_ID),(t)-> ModBlockRenderer.RegisterRenderers(BlockModelDispatcher.getInstance()));
			ClientEvents.ENTITY_RENDERER_RELOAD.listen(Key.of(MOD_ID), ModEntityRenderer::RegisterRenderers);
			ParticleDispatcher.getInstance().addDispatch("dripBlood",
				(world,
				 x, y, z,
				 motionX, motionY, motionZ,
				 data)
				-> new ParticleFluidDrip(world, x, y, z, 0xFF0000,
				TextureRegistry.getTexture("theswitch:block/blood/flowing")));
		}

		LOGGER.info("The Switch initialized.");
	}

	public void beforeGameStart() {
		ModBlocks.InitBlocks();
		ModItems.InitItems();
		ModEntities.InitEnemies(EntityDispatcher.getInstance());
		THE_SWITCH_BIOME = Biomes.register("theswitch:overworld.theswitch", new TheSwitchBiome("overworld.theswitch")
			.withDebugColor(11184810).withPlacementDefaults(0.625F, 0.25F, .5F) // Change variety in future
			.withTags(BiomeTags.HAS_SURFACE_WATER)
		);

		SoundTypes.loadSoundsJson(MOD_ID);
	}

	public void afterGameStart() {

	}

	public void onRecipesReady()
	{
		ModRecipes.initializeRecipes();
	}
}
