package varianttombstones.theswitch.mixin;

import net.minecraft.core.world.World;
import net.minecraft.core.world.biome.Biomes;
import net.minecraft.core.world.biome.data.BiomeRange;
import net.minecraft.core.world.biome.data.BiomeRangeMap;
import net.minecraft.core.world.biome.provider.BiomeProvider;
import net.minecraft.core.world.biome.provider.BiomeProviderOverworld;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import varianttombstones.theswitch.TheSwitch;

@Mixin(BiomeProviderOverworld.class)
public abstract class BiomeProviderOverworldMixin extends BiomeProvider {
	@Shadow
	@Final
	private static BiomeRangeMap brm;

	public BiomeProviderOverworldMixin(@NotNull World world) {
		super(world);
	}

	@Inject(
		method = "init()V",
		at =  @At(
			value = "INVOKE",
			target = "Lnet/minecraft/core/world/biome/data/BiomeRangeMap;lock()V"
		)
	)
	private static void init(CallbackInfo ci) {
		brm.addRange(TheSwitch.THE_SWITCH_BIOME, new BiomeRange((double)0.5F, 1.5F, (double)0.15F, 0.5, (double)0.0F, (double)1.0F, (double)0.0F, (double)1.0F));
	}
}

