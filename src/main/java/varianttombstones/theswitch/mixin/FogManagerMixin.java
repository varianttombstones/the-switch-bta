package varianttombstones.theswitch.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.client.Minecraft;
import net.minecraft.client.render.FogManager;
import net.minecraft.core.util.helper.MathHelper;
import net.minecraft.core.world.biome.Biomes;
import net.minecraft.core.world.pos.TilePos;
import org.joml.Vector4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import varianttombstones.theswitch.TheSwitch;

@Mixin(FogManager.class)
public class FogManagerMixin {
	private float switchFogTransition = 0.0F;

	@ModifyReturnValue(
		method = "setupFog",
		at = @At("RETURN")
	)
	private FogManager.FogState switchBiomeFog(FogManager.FogState dest) {
		Minecraft mc = Minecraft.getMinecraft();
		TilePos cameraTile = mc.activeCamera.getTilePos();

		float farPlaneDistance = mc.worldRenderer.farPlaneDistance;
		float fogTransitionSpeed = 5.0E-4F;
		if (mc.currentWorld.getBlockBiome(cameraTile) == TheSwitch.THE_SWITCH_BIOME) {
			this.switchFogTransition += fogTransitionSpeed;
		} else {
			this.switchFogTransition -= fogTransitionSpeed;
		}

		this.switchFogTransition = MathHelper.clamp(this.switchFogTransition, 0.0F, 1.0F);
		if (this.switchFogTransition > 0.0F) {
			dest.fogStart += (farPlaneDistance * 0.01F - dest.fogStart) * this.switchFogTransition;
			dest.fogEnd += (farPlaneDistance * 0.1F - dest.fogEnd) * this.switchFogTransition;
		}
		return dest;
	}
}
