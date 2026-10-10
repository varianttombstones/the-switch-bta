package varianttombstones.theswitch.mixin;

import net.minecraft.core.block.Blocks;
import net.minecraft.core.world.World;
import net.minecraft.core.world.generate.feature.WorldFeatureOre;
import net.minecraft.core.world.pos.TilePosc;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import varianttombstones.theswitch.TheSwitch;
import varianttombstones.theswitch.block.ModBlocks;

import java.util.Random;

@Mixin(WorldFeatureOre.class)
public class WorldFeatureOreMixin {
	@Mutable
	@Final
	@Shadow
	private int minableBlockId;

	@Inject(
		method="place",
		at=@At("HEAD")
	)
	private void dirtReplacement(World world, Random random, TilePosc tilePos, CallbackInfoReturnable<Boolean> cir)
	{
		if (minableBlockId == Blocks.DIRT.id())
		{
			if (world.getBlockBiome(tilePos) == TheSwitch.THE_SWITCH_BIOME)
			{
				if (random.nextInt(3) != 2)
				{
					minableBlockId = ModBlocks.FLESH.id();
				}
			}
		}
	}
}
