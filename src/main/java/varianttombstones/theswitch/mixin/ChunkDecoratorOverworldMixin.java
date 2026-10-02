package varianttombstones.theswitch.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.core.world.biome.Biome;
import net.minecraft.core.world.biome.Biomes;
import net.minecraft.core.world.chunk.Chunk;
import net.minecraft.core.world.generate.chunk.ChunkDecorator;
import net.minecraft.core.world.generate.chunk.perlin.overworld.ChunkDecoratorOverworld;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import varianttombstones.theswitch.TheSwitch;

@Mixin(ChunkDecoratorOverworld.class)
public class ChunkDecoratorOverworldMixin {
	@Inject(
		method = "decorate",
		at =  @At(
			value = "INVOKE",
			target = "Ljava/util/Random;nextInt(I)I",
			ordinal = 58
			//shift = At.Shift.AFTER
		)
	)
	public void injectTrees(Chunk chunk, CallbackInfo ci,
							@Local(name="biome") Biome biome,
							@Local(name="treeDensity") int treeDensity,
							@Local(name="k4") int k4) {
		if (biome == TheSwitch.THE_SWITCH_BIOME) {
			treeDensity += k4 + 4;
		}
	}
}
