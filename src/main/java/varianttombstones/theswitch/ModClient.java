package varianttombstones.theswitch;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.render.item.model.ItemModelDispatcher;
import net.minecraft.client.render.texture.stitcher.AtlasStitcher;
import net.minecraft.client.render.texture.stitcher.TextureRegistry;
import net.minecraft.client.sound.SoundRepository;
import turniplabs.halplibe.event.defs.ClientEvents;
import turniplabs.halplibe.helper.TextureHelper;
import turniplabs.halplibe.util.dependency.Key;

import static varianttombstones.theswitch.TheSwitch.MOD_ID;

public class ModClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		ClientEvents.BEFORE_CLIENT_START.listen(Key.of(MOD_ID), ModClient::beforeClientStart);
		ClientEvents.ITEM_MODEL_RELOAD.listen(Key.of(MOD_ID), ModClient::initItemModels);
	}

	public static void beforeClientStart() {
		ModClient.registerTextures();
		SoundRepository.namespaceAdded(MOD_ID);
	}

	public static void registerTextures() {
		for (final AtlasStitcher stitcher : TextureRegistry.stitcherMap.values()) {
			TextureHelper.initializeAllFiles(MOD_ID, stitcher, true);
		}
	}

	public static void initItemModels(ItemModelDispatcher dispatcher) {

	}

}
