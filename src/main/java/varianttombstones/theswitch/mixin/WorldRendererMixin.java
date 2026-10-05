package varianttombstones.theswitch.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalRef;
import net.minecraft.client.render.WorldRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WorldRenderer.class)
public class WorldRendererMixin {
	@Inject(method = "addRainParticles()V", at = @At(value = "INVOKE", target = "world.getCurrentWeather()V", shift = At.Shift.AFTER))
	private void setBloodRain(CallbackInfo ci, @Local LocalRef<String> localRef)
	{

	}
}
