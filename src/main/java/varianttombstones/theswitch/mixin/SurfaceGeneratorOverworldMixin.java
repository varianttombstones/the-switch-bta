package varianttombstones.theswitch.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.core.block.Blocks;
import net.minecraft.core.world.biome.Biome;
import net.minecraft.core.world.biome.Biomes;
import net.minecraft.core.world.chunk.Chunk;
import net.minecraft.core.world.generate.chunk.ChunkGeneratorResult;
import net.minecraft.core.world.generate.chunk.perlin.SurfaceGenerator;
import net.minecraft.core.world.generate.chunk.perlin.overworld.SurfaceGeneratorOverworld;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import varianttombstones.theswitch.TheSwitch;
import varianttombstones.theswitch.block.ModBlocks;

import java.util.Random;

@Mixin(SurfaceGeneratorOverworld.class)
public class SurfaceGeneratorOverworldMixin {


	@Inject(
		method = "generateSurface",
		at = @At(
			value = "INVOKE",
			target = "Ljava/util/Random;nextInt(I)I",
			ordinal = 3,
			shift = At.Shift.BEFORE
		)
	)
	public void generateSurface(Chunk chunk, ChunkGeneratorResult result, CallbackInfo ci,
								@Local(name="biome") Biome biome,
								@Local(name="x") int x,
								@Local(name="y") int y,
								@Local(name="z") int z,
								@Local(name="minY") int minY,
								@Local(name="rand") Random rand
								) {
		if (biome == TheSwitch.THE_SWITCH_BIOME) {
			result.setBlock(x, y, z, ModBlocks.FLESH.id());
		}
	}
}
