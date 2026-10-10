package varianttombstones.theswitch.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.core.block.Blocks;
import net.minecraft.core.world.World;
import net.minecraft.core.world.biome.Biome;
import net.minecraft.core.world.biome.Biomes;
import net.minecraft.core.world.chunk.Chunk;
import net.minecraft.core.world.generate.chunk.ChunkDecorator;
import net.minecraft.core.world.generate.chunk.perlin.overworld.ChunkDecoratorOverworld;
import net.minecraft.core.world.generate.feature.WorldFeatureDungeon;
import net.minecraft.core.world.generate.feature.WorldFeatureOre;
import net.minecraft.core.world.pos.TilePos;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import varianttombstones.theswitch.TheSwitch;
import varianttombstones.theswitch.block.ModBlocks;
import varianttombstones.theswitch.world.feature.WorldFeatureCrucifix;
import varianttombstones.theswitch.world.feature.WorldFeatureHeart;

import java.util.Random;

@Mixin(ChunkDecoratorOverworld.class)
public class ChunkDecoratorOverworldMixin {
	@Shadow
	private World world;

	@ModifyVariable(
		method = "decorate",
		at =  @At("STORE"),
		name = "treeDensity"
	)
	private int modifySwitchBiomeTreeDensity(
		int treeDensity,
		@Local(name="biome") Biome biome,
		@Local(name="k4") int k4)
	{
		if (biome == TheSwitch.THE_SWITCH_BIOME) {
			treeDensity += k4 + 25;
		}

		return treeDensity;
	}

	//
	@Inject(
		method = "decorate",
		at = @At(
			value = "INVOKE",
			target = "Ljava/util/Random;nextInt(I)I",
			ordinal = 58
		)
	)
	private void injectHeart(Chunk chunk, CallbackInfo ci,
							 @Local(name="biome") Biome biome,
							 @Local(name="rand") Random rand,
							 @Local(name="oreHeightModifier") float oreHeightModifier,
							 @Local(name="rangeY") int rangeY,
							 @Local(name="minY") int minY,
							 @Local(name="x") int x,
							 @Local(name="z") int z
	)
	{
		if (biome == TheSwitch.THE_SWITCH_BIOME) {
			for(int crucifixAttempts = 0; (float)crucifixAttempts < 100; ++crucifixAttempts) {
				int xPos = x + rand.nextInt(16) + 8;
				int yPos = minY + rand.nextInt(rangeY);
				int zPos = z + rand.nextInt(16) + 8;
				new WorldFeatureCrucifix().place(this.world, rand, xPos, yPos, zPos);
			}

			for(int k1 = 0; (float)k1 < 8.0F * oreHeightModifier; ++k1) {
				int j5 = x + rand.nextInt(16) + 8;
				int k8 = minY + rand.nextInt(rangeY);
				int j11 = z + rand.nextInt(16) + 8;
				new WorldFeatureHeart().place(this.world, rand, j5, k8, j11);
			}

			for(int k1 = 0; (float)k1 < 8.0F * oreHeightModifier; ++k1) {
				int j5 = x + rand.nextInt(16) + 8;
				int k8 = minY + rand.nextInt(rangeY);
				int j11 = z + rand.nextInt(16) + 8;
				TilePos queryPos = new TilePos(j5, k8, j11);
				new WorldFeatureOre(ModBlocks.FLESH.id(), 32).place(this.world, rand, queryPos);
			}
		}
	}
}
